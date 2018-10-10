package com.taofeng.webcast.common.query;

import java.io.Serializable;
import java.util.List;

/**
 * <p>描述</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/2/25 下午4:50
 * @since V1.0
 */
public class Criterion implements Serializable {

    private static final long serialVersionUID = -4164344602073471548L;

    protected String condition;

    protected Object value;

    protected Object secondValue;

    protected boolean noValue;

    protected boolean singleValue;

    protected boolean betweenValue;

    protected boolean listValue;

    protected String typeHandler;

    public String getCondition() {
        return condition;
    }

    public Object getValue() {
        return value;
    }

    public Object getSecondValue() {
        return secondValue;
    }

    public boolean isNoValue() {
        return noValue;
    }

    public boolean isSingleValue() {
        return singleValue;
    }

    public boolean isBetweenValue() {
        return betweenValue;
    }

    public boolean isListValue() {
        return listValue;
    }

    public String getTypeHandler() {
        return typeHandler;
    }

    public Criterion(String condition) {
        super();
        this.condition = condition;
        this.typeHandler = null;
        this.noValue = true;
    }

    public Criterion(String condition, Object value, String typeHandler) {
        super();
        this.condition = condition;
        this.value = value;
        this.typeHandler = typeHandler;
        if (value instanceof List<?>) {
            this.listValue = true;
        } else {
            this.singleValue = true;
        }
    }

    public Criterion(String condition, Object value) {
        this(condition, value, null);
    }

    public Criterion(String condition, Object value, Object secondValue, String typeHandler) {
        super();
        this.condition = condition;
        this.value = value;
        this.secondValue = secondValue;
        this.typeHandler = typeHandler;
        this.betweenValue = true;
    }

    public Criterion(String condition, Object value, Object secondValue) {
        this(condition, value, secondValue, null);
    }


    @Override
    public String toString() {
        if (isNoValue()) {
            return condition;
        } else if (isSingleValue()) {
            return condition + " " + this.getValue();
        } else if (isBetweenValue()) {
            return condition + " " + this.value + " and " + this.secondValue;
        } else if (isListValue()) {
            return condition + " in " + this.value;
        } else {
            return "invalid status";
        }
    }
}
