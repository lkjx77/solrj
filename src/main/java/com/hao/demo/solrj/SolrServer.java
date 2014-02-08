package com.hao.demo.solrj;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.common.SolrInputDocument;

public class SolrServer {

	private CloudSolrServer server;
	
	private String zkHost = "192.168.127.128:2181";
	private String collection = "paimai";
	private int zkClientTimeout = 100000;
	private int zkConnectTimeout = 100000;

	public SolrServer() {
		try {
			server = new CloudSolrServer(zkHost);
			server.setDefaultCollection(collection);
			server.setZkClientTimeout(zkClientTimeout);
			server.setZkConnectTimeout(zkConnectTimeout);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	public void add(){
		Collection<SolrInputDocument> ls = new ArrayList<SolrInputDocument>();
		
		for(int i=0;i<3000;i++){
			SolrInputDocument doc = new SolrInputDocument ();
			doc.addField("id", i);
			doc.addField("name", "中国" + i);
			doc.addField("price", 50.5 + i);
			doc.addField("age", 30 + i);
			
			ls.add(doc);
		}
		try {
			server.add(ls);
			server.commit();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteById(){
		List<String> ids = new ArrayList<String>();
		ids.add("0");
		ids.add("1");
		ids.add("2");
		ids.add("3");
		ids.add("4");
		try {
			server.deleteById(ids);
			server.commit();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		SolrServer solrServer = new SolrServer();
//		solrServer.add();
		solrServer.deleteById();
	}

}
