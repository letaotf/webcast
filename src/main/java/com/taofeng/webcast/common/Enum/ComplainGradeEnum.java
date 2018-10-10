package com.taofeng.webcast.common.Enum;

/**
 * <p>投诉分数</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/5/11 下午3:21
 * @since V1.0
 */
public enum ComplainGradeEnum {

    FIVE(5.0,ComplainRankEnum.THIRD_CLASS),
    TEN(10.0,ComplainRankEnum.SECOND_CLASS),
    FIFTEEN(15.0,ComplainRankEnum.First_CLASS),;

    private Double grade;
    private ComplainRankEnum complainRankEnum;

    ComplainGradeEnum(Double grade,ComplainRankEnum complainRankEnum){
        this.grade = grade;
        this.complainRankEnum = complainRankEnum;
    }

    public Double getGrade() {
        return grade;
    }

    public ComplainRankEnum getComplainRankEnum() {
        return complainRankEnum;
    }

    /**
     * 根据投诉等级枚举
     * @param complainRankEnum
     * @return
     */
    public static Double getMsgByCode(ComplainRankEnum complainRankEnum){
        ComplainGradeEnum[] roles = values();
        for (ComplainGradeEnum role : roles){
            if (role.getComplainRankEnum().equals(complainRankEnum)){
                return role.getGrade();
            }
        }
        return null;
    }
}
