package com.water.example.luceneexample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.SortField.Type;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Before;
import org.junit.Test;

import com.chenlb.mmseg4j.ComplexSeg;
import com.chenlb.mmseg4j.Dictionary;
import com.chenlb.mmseg4j.MMSeg;
import com.chenlb.mmseg4j.MaxWordSeg;
import com.chenlb.mmseg4j.Seg;
import com.chenlb.mmseg4j.Word;
import com.chenlb.mmseg4j.analysis.ComplexAnalyzer;
import com.chenlb.mmseg4j.analysis.MaxWordAnalyzer;
import com.chenlb.mmseg4j.example.Complex;

public class LuceneTest {
	private File datadirectory;
	private File indexdirector;
	private Analyzer analyzer;
	private IndexWriter indexWriter;
	private Seg seg = null;

	private FileFilter fileFilter = new FileFilter() {
		@Override
		public boolean accept(File pathname) {
			System.out.println(pathname.getName());
			if(pathname.isDirectory()){
				return true;
			}
			if (pathname.canRead() && (pathname.getName().endsWith(".txt") || pathname.getName().endsWith(".html"))) {
				return true;
			}
			return false;
		}
	};

	@Before
	public void init() throws Exception {
		try {
			indexdirector = new File("D:/luceneindex");
			datadirectory = new File("D:\\coding\\testdata\\htmlfiles");
			// analyzer = new StandardAnalyzer(Version.LUCENE_46);new
			// ComplexAnalyzer("");
			analyzer = new ComplexAnalyzer();
			analyzer = new MaxWordAnalyzer();

			//seg = new MaxWordSeg(Dictionary.getInstance()); // 取得不同的分词具体算法
			String path = LuceneTest.class.getResource("/").getPath()+"dicdata/";
			seg = new MaxWordSeg(Dictionary.getInstance(path)); // 取得不同的分词具体算法
			/*
			IndexWriterConfig indexWriterConfig = new IndexWriterConfig(
					Version.LUCENE_43, analyzer);
			indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE);

			indexWriter = new IndexWriter(FSDirectory.open(indexdirector),
					indexWriterConfig);
			for (File file : datadirectory.listFiles(fileFilter)) {
				System.out.println(file.getName());
				todocument(indexWriter, file);
			}
			// indexWriter.commit();
			indexWriter.close();*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String segWords(Reader input, String wordSpilt) {
		StringBuilder sb = new StringBuilder();
		try {
			MMSeg mmSeg = new MMSeg(input, seg);

			Word word = null;
			boolean first = true;
			while ((word = mmSeg.next()) != null) {
				if (!first) {
					sb.append(wordSpilt);
				}
				String w = word.getString();
				sb.append(w);
				first = false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	private String segWords(String txt, String wordSpilt) {
		return segWords(new StringReader(txt), wordSpilt);
	}

	/**
	 * 1,如果内容为文章中,搜索高亮; (以匹配度最高的文章片段)
	 * 
	 * @param indexWriter
	 * @param file
	 * @throws IOException
	 */
	private void todocument(IndexWriter indexWriter, File file)
			throws IOException {
		if (file.isDirectory()) {
			for (File tempfile : file.listFiles(fileFilter)) {
				todocument(indexWriter, tempfile);
			}
			return;
		}
		FileInputStream fileInputStream = null;
		try {

			Document document = new Document();
			document.add(new StringField("path", file.getAbsolutePath(),Field.Store.YES));
			document.add(new StringField("title", file.getName().substring(	file.getName().lastIndexOf('/') < 0 ? 0 : file.getName().lastIndexOf('/')), Field.Store.YES));
			// 内容注意编码格式，不然出现中文乱码
			document.add(new TextField("content", FileUtils.readFileToString(file, "UTF-8"), Field.Store.YES));
			// 不存储内容
			//document.add(new TextField("content",new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"))));

			document.add(new LongField("modifydate", file.lastModified(),
					Field.Store.YES));

			indexWriter.addDocument(document);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fileInputStream != null) {
				fileInputStream.close();
			}
		}
	}

	private void executeQuery(Query query, int n, Sort sort) {
		try {
			long stime = System.currentTimeMillis();
			IndexSearcher indexSearcher = new IndexSearcher(DirectoryReader.open(FSDirectory.open(indexdirector)));
			TopDocs topDocs = indexSearcher.search(query, n, sort);
			System.out.println("命中：" + topDocs.totalHits);
			
			SimpleHTMLFormatter formatter = new SimpleHTMLFormatter("<font color=\"red\">", "</font>");
			QueryScorer scorer=new QueryScorer(query);
			Fragmenter fragmenter = new SimpleSpanFragmenter(scorer);  
	        Highlighter highlight=new Highlighter(formatter,scorer);  
	        highlight.setTextFragmenter(fragmenter);
	        
	        System.out.println(System.currentTimeMillis() - stime);
	        
			for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
				System.out.println("权重：" + scoreDoc.score);
				Document document = indexSearcher.doc(scoreDoc.doc);
				System.out.println("标题：" + document.get("title"));
				System.out.println("时间："	+ new Date(Long.parseLong(document.get("modifydate")))+"  | "+document.get("modifydate"));
				//System.out.println("内容："+document.get("content"));
				
				if(document.get("content") != null){
					TokenStream tokenStream = analyzer.tokenStream("content",new StringReader(document.get("content")));    
	                String highLightText = highlight.getBestFragment(tokenStream, document.get("content")); 
	                System.out.println("高亮内容："+highLightText);
				}
		        
				
				System.out.println("");

			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidTokenOffsetsException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 全字匹配
	 */
	@Test
	public void testQuery1() {
		Term term = new Term("title", "cms5常用代码.txt");
		Query termQuery = new TermQuery(term);
		SortField sortFields[] = { new SortField("modifydate", Type.LONG, true) // 倒序
		};
		Sort sort = new Sort(sortFields);
		executeQuery(termQuery, 10, sort);
	}

	/**
	 * 模糊搜索
	 */
	@Test
	public void testQuery2() {
		Term term = new Term("title", "cms*");
		Query query = new WildcardQuery(term);
		SortField sortFields[] = { new SortField("modifydate", Type.LONG, true) // 倒序
		};
		Sort sort = new Sort(sortFields);
		executeQuery(query, 10, sort);
	}

	/**
	 * 相似度搜索
	 */
	@Test
	public void testQuery3() {
		String keywork = "查看磁盘文件夹的大小";
		String strs[] = segWords(keywork, "|").split("\\|");
		Set<Term> terms = new HashSet<Term>();
		for (String str : strs) {
			terms.add(new Term("content", str));
		}
		Term term = new Term("content", keywork);
		Query query = new FuzzyQuery(term, 2);
		// query.extractTerms(terms);

		SortField sortFields[] = { new SortField("modifydate", Type.LONG, true) // 倒序
		};
		Sort sort = new Sort(sortFields);
		executeQuery(query, 10, sort);
	}

	/**
	 * 组合查询
	 * 
	 * @throws ParseException
	 */
	@Test
	public void testQuery4() throws ParseException {
		String keywork = "广州市的";
		System.out.println(segWords(keywork, "|"));

		BooleanQuery booleanQuery = new BooleanQuery();
		
		Term term = new Term("title", "*"+keywork+"*");
		Query wildcardquery = new WildcardQuery(term);
		wildcardquery.setBoost(2.0f);
		booleanQuery.add(wildcardquery, BooleanClause.Occur.SHOULD);
		
		//时间范围限制
		//Query numberquery = NumericRangeQuery.newLongRange("modifydate", 1387869738625L, 1387869738626L,true,true);
		//booleanQuery.add(numberquery, BooleanClause.Occur.MUST);
		
		BooleanClause.Occur[] flags = { BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD };// 
		Query query = MultiFieldQueryParser.parse(Version.LUCENE_43, keywork,new String[] {"title", "content" }, flags, analyzer);
		query.setBoost(0.8f);
		booleanQuery.add(query, BooleanClause.Occur.SHOULD);
		
		
		SortField sortFields[] = {
			SortField.FIELD_SCORE,
			new SortField("title", Type.STRING),
			new SortField("modifydate", Type.LONG, true) // 倒序
		};
		Sort sort = new Sort(sortFields);
		executeQuery(booleanQuery, 5, sort);
	}
}
