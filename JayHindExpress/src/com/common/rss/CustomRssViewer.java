package com.common.rss;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.feed.AbstractRssFeedView;

import com.models.SampleContent;
import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.feed.rss.Content;
import com.sun.syndication.feed.rss.Description;
import com.sun.syndication.feed.rss.Item;

import javafx.util.BuilderFactory;


public class CustomRssViewer extends AbstractRssFeedView2 {
 	 
	@Override
	protected void buildFeedMetadata(Map<String, Object> model, Channel feed,
		HttpServletRequest request) {

		feed.setTitle("Jay Hind Express");
		feed.setDescription("News Feed");
		feed.setLink("http://www.jayhind.com");

		feed.setEncoding("UTF-8");
		feed.setFeedType("rss_2.0");
		feed.setCopyright("All rights reserved by Jay Hind Express.");
		
		feed.setLanguage("Hindi");
		
		
		super.buildFeedMetadata(model, feed, request);
	}


	@Override
	protected List<Item> buildFeedItems(Map<String, Object> model,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		@SuppressWarnings("unchecked")
		List<SampleContent> listContent = (List<SampleContent>) model.get("feedContent");
		List<Item> items = new ArrayList<Item>(listContent.size());

		for(SampleContent tempContent : listContent ){

			Item item = new Item();
			
			Content content = new Content();
			content.setValue(tempContent.getSummary());
			content.setType(Content.HTML);
			
			//Description d = new Description();
			//d.setType(Content.HTML);
			//d.setValue(tempContent.getSummary());
			
			//byte pbyte[] = tempContent.getSummary().getBytes(Charset.forName("UTF-8"));
			//String newstr = new String(pbyte,"UTF-8");
			////System.out.println(pbyte.toString());
			//d.setValue(newstr);
			
			item.setContent(content);
			item.setTitle(tempContent.getTitle());
			item.setLink(tempContent.getUrl());
			item.setPubDate(tempContent.getCreatedDate());
			item.setAuthor(tempContent.getAuthor());
			//item.setDescription(d);
			
			items.add(item);
			
			
		}
		response.setCharacterEncoding("UTF-8");
		return items;
	}
}
