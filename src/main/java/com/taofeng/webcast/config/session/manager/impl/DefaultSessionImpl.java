package com.taofeng.webcast.config.session.manager.impl;


import com.taofeng.webcast.config.session.manager.Session;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * <p>提供的sessionid获取用户id</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/4/20 下午7:22
 * @since V1.0
 */
@AllArgsConstructor
@Data
public class DefaultSessionImpl implements Session, Serializable {
	private static final long serialVersionUID = 6294354616416758926L;

	private static final ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>(){
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
	};

	/**
	 * sessionId
	 */
	private String sessionId;

	/**
	 * 人员信息
	 */

	private HashMap<String, Object> attrs = new HashMap<>();

	/**
	 * sessionId 的创建时间
	 */
	private long lastActiveTime;

	/**
	 *
	 */
	private boolean isNew = true;

	/**
	 * 构造函数,sessionId有效期,重创建开始
	 * @param sessionId
	 */
	public DefaultSessionImpl(String sessionId) {
		if (StringUtils.isBlank(sessionId)) {
			throw new IllegalArgumentException("sessionId is blank");
		}
		this.sessionId = sessionId;
		lastActiveTime = new Date().getTime();
	}

	/**
	 * 设置attrs
	 * @param key
	 * @param value
	 */
	@Override
	public void setAttribute(String key, Object value) {
		if (StringUtils.isBlank(key)) {
			throw new IllegalArgumentException("key is blank.");
		}
		attrs.put(key, value);
	}

	/**
	 * 根据key值获取  attrs 中的值
	 * @param key
	 * @return
	 */
	@Override
	public Object getAttribute(String key) {
		if (StringUtils.isBlank(key)) {
			throw new IllegalArgumentException("key is blank.");
		}
		return attrs.get(key);
	}

	/**
	 * 判断attrs 中是否存在相应的key值
	 * @param key
	 * @return
	 */
	@Override
	public boolean existAttribute(String key) {
		if (StringUtils.isBlank(key)) {
			throw new IllegalArgumentException("key is blank.");
		}
		return attrs.containsKey(key);
	}

	/**
	 * 删除attrs 中key对应的值
	 * @param key
	 */
	@Override
    public void removeAttribute(String key) {
	    if (StringUtils.isBlank(key)) {
			throw new IllegalArgumentException("key is blank.");
		}
	    attrs.remove(key);
    }

	/**
	 * 设置sessionId 的有效时间
	 * @param time
	 */
	@Override
	public void update(long time) {
		this.lastActiveTime = time;
		isNew = false;
	}

	/**
	 *
	 * @return
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", sessionId).append("attrs", attrs)
				.append("lastActiveTime", df.get().format(new Date(lastActiveTime))).toString();
	}
}
