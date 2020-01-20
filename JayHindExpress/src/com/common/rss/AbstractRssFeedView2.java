package com.common.rss;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.feed.rss.Item;

import org.springframework.http.MediaType;
import org.springframework.web.servlet.view.feed.AbstractFeedView;

public abstract class AbstractRssFeedView2 extends AbstractFeedView<Channel>{

	public AbstractRssFeedView2() {
		setContentType(MediaType.APPLICATION_ATOM_XML_VALUE);
	}


	/**
	 * Create a new Channel instance to hold the entries.
	 * <p>By default returns an RSS 2.0 channel, but the subclass can specify any channel.
	 */
	@Override
	protected Channel newFeed() {
		return new Channel("rss_2.0");
	}

	/**
	 * Invokes {@link #buildFeedItems(Map, HttpServletRequest, HttpServletResponse)}
	 * to get a list of feed items.
	 */
	@Override
	protected final void buildFeedEntries(Map<String, Object> model, Channel channel,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<Item> items = buildFeedItems(model, request, response);
		channel.setEncoding("UTF-8");
		channel.setItems(items);
		channel.setEncoding("UTF-8");
	}

	/**
	 * Subclasses must implement this method to build feed items, given the model.
	 * <p>Note that the passed-in HTTP response is just supposed to be used for
	 * setting cookies or other HTTP headers. The built feed itself will automatically
	 * get written to the response after this method returns.
	 * @param model	the model Map
	 * @param request  in case we need locale etc. Shouldn't look at attributes.
	 * @param response in case we need to set cookies. Shouldn't write to it.
	 * @return the feed items to be added to the feed
	 * @throws Exception any exception that occurred during document building
	 * @see Item
	 */
	protected abstract List<Item> buildFeedItems(
			Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
throws Exception;
	
}
