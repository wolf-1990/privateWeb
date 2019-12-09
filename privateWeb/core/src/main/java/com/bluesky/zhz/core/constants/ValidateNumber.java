package com.bluesky.zhz.core.constants;

public interface ValidateNumber {

    int VALUE_MAX = 10;
    int VALUE_MIN = 0;
    String VALUE_MESSAGE = "属性值范围0-10";

    int EXPIRE_MAX_DAY = 365;
    int EXPIRE_MIN_DAY = 1;
    String EXPIRE_MESSAGE = "实效时间最多1年最少１天";

    String NATIVE_PRICE_MAX = "20000.00";
    String NATIVE_PRICE_MIN = "1.00";
    String NATIVE_PRICE_MESSAGE = "原价不能超过2万元,小于1元";

    String REAL_PRICE_MAX = "20000.00";
    String REAL_PRICE_MIN = "1.00";
    String REAL_PRICE_MESSAGE = "现价不能超过2万元,小于1元";

    int PAGE_NUMBER_MAX = 100000;
    int PAGE_NUMBER_MIN = 0;
    String PAGE_NUMBER_MESSAGE = "页码最大100000,最小0";

    int PAGE_COUNT_MAX = 50;
    int PAGE_COUNT_MIN = 1;
    String PAGE_COUNT_MESSAGE = ",每页数据最大50,最小1";

    int SORT_MAX = 200;
    int SORT_MIN = 0;
    String SORT_MESSAGE = "排序最大200,最小0";
    
    int GRADE_MAX = 10;
    int GRADE_MIN = 0;
    String GRADE_MESSAGE = "年级最大10,最小0";
    
    int CREDIT_MAX = 100;
    int CREDIT_MIN = 1;
    String CREDIT_MESSAGE = "学分最大100,最小0";
    
    int SEX_MAX = 1;
    int SEX_MIN = 0;
    String SEX_MESSAGE = "性别最大1,最小0";
    
    int IS_TREE_MAX = 1;
    int IS_TREE_MIN = 0;
    String IS_TREE_MESSAGE = "是否需要树形结构最大1,最小0";
    
    int IS_OPEN_MAX = 1;
    int IS_OPEN_MIN = 0;
    String IS_OPEN_MESSAGE = "是否开放最大1,最小0";
    
    int IS_CAN_APPLY_MAX = 1;
    int IS_CAN_APPLY_MIN = 0;
    String IS_CAN_APPLY_MESSAGE = "是否可报名最大1,最小0";
    
    int OPERATE_TYPE_MAX = 1;
    int OPERATE_TYPE_MIN = 0;
    String OPERATE_TYPE_MESSAGE = "操作类型最大1,最小0";
    
    int IS_UNDERGRADUATE_MAX = 1;
    int IS_UNDERGRADUATE_MIN = 0;
    String IS_UNDERGRADUATE_MESSAGE = "是否为本科最大1,最小0";
    
    int STUDENT_STATUS_MAX = 10;
    int STUDENT_STATUS_MIN = 0;
    String STUDENT_STATUS_MESSAGE = "学生在校状态最大10,最小0";

    long GRADE_ID_MAX = 999999;
    long GRADE_ID_MIN = 0;
    String GRADE_ID_MESSAGE = "年级id在0-999999之间";
    
    int SEMESTER_MAX = 10;
    int SEMESTER_MIN = 0;
    String SEMESTER_MESSAGE = "学期最大10,最小0";
    
    int TEACHER_KIND_MAX = 10;
    int TEACHER_KIND_MIN = 0;
    String TEACHER_KIND_MESSAGE = "教师性质最大10,最小0";
    
    int DIFFICULTY_MAX = 10;
    int DIFFICULTY_MIN = 0;
    String DIFFICULTY_MESSAGE = "试卷难度最大10,最小0";

    long PERIOD_ID_MAX = 999999;
    long PERIOD_ID_MIN = 0;
    String PERIOD_ID_MESSAGE = "学期id在0-999999之间";
    
    long AREA_ID_MAX = 999999;
    long AREA_ID_MIN = 0;
    String AREA_ID_MESSAGE = "地区id在0-999999之间";
    
    long ACADEMIC_HOUR_MAX = 999999;
    long ACADEMIC_HOUR_MIN = 0;
    String ACADEMIC_HOUR_MESSAGE = "课时数在0-999999之间";
    
    long TEST_MINUTES_MAX = 999999;
    long TEST_MINUTES_MIN = 0;
    String TEST_MINUTES_MESSAGE = "考试时长在0-999999之间";
    
    long EVERY_SCORE_MAX = 999999;
    long EVERY_SCORE_MIN = 0;
    String EVERY_SCORE_MESSAGE = "每题分数在0-999999之间";
    
    long TOTAL_SCORE_MAX = 999999;
    long TOTAL_SCORE_MIN = 0;
    String TOTAL_SCORE_MESSAGE = "试卷总分在0-999999之间";
    
    long PASSING_SCORE_MAX = 999999;
    long PASSING_SCORE_MIN = 0;
    String PASSING_SCORE_MESSAGE = "试卷及格分数在0-999999之间";

    long TAG_ID_MAX = 999999;
    long TAG_ID_MIN = 0;
    String TAG_ID_MESSAGE = "标签id在0-999999之间";

    int COLUMN_NAME_MAX = 100;
    int COLUMN_NAME_MIN = 0;
    String COLUMN_NAME_MESSAGE = "栏目名称长度在0-100之间";

    long ORDER_NUMBER_MAX = 999999;
    long ORDER_NUMBER_MIN = 0;
    String ORDER_NUMBER_MESSAGE = "顺序号范围0-999999";

    int SIMPLE_NAME_MAX = 1000;
    int SIMPLE_NAME_MIN = 0;
    String SIMPLE_NAME_MESSAGE = "简称范围0-1000";

    int TITLE_LENGTH_MAX = 1000;
    int TITLE_LENGTH_MIN = 0;
    String TITLE_LENGTH_MESSAGE = "标题范围0-1000";

    int CONTENT_LENGTH_MAX = 100000000;
    int CONTENT_LENGTH_MIN = 0;
    String CONTENT_LENGTH_MESSAGE = "内容范围0-100000000";

    int SUMMARY_LENGTH_MAX = 1000;
    int SUMMARY_LENGTH_MIN = 0;
    String SUMMARY_LENGTH_MESSAGE = "摘要范围0-1000";

    int COLUMN_ID_MAX = 999999;
    int COLUMN_ID_MIN = 0;
    String COLUMN_ID_MESSAGE = "栏目id长度0-999999";
    
    int TEMPLATE_TYPE_MAX = 10;
    int TEMPLATE_TYPE_MIN = 0;
    String TEMPLATE_TYPE_MESSAGE = "新闻模板类型范围0-10";

    int AUTHOR_NAME_MAX = 50;
    int AUTHOR_NAME_MIN = 0;
    String AUTHOR_NAME_MESSAGE = "作者名称范围0-59";

    int COLUMN_CONTENT_MAX = 99999;
    int COLUMN_CONTENT_MIN = 0;
    String COLUMN_CONTENT_MESSAGE = "栏目id长度0-99999";

    int TEMPLATE_ID_MAX = 999999;
    int TEMPLATE_ID_MIN = 0;
    String TEMPLATE_ID_MESSAGE = "模板id长度0-999999";

    int ADVERTISEMENT_NAME_MAX = 200;
    int ADVERTISEMENT_NAME_MIN = 0;
    String ADVERTISEMENT_NAME_MESSAGE = "广告位名称0-200";

    int SINGLE_PAGE_CONTENT_MAX = 999999;
    int SINGLE_PAGE_CONTENT_MIN = 0;
    String SINGLE_PAGE_CONTENT_MESSAGE = "单页详细内容范围0-999999";

    int WEB_SITE_NAME_MAX = 500;
    int WEB_SITE_NAME_MIN = 0;
    String WEB_SITE_NAME_MESSAGE = "网站名称范围0-500";
    
    String ORDER_TOTLEFEE_SIZE_MAX = "99999";
    String ORDER_TOTLEFEE_SIZE_MIN = "0.01";
    String ORDER_TOTLEFEE_MESSAGE = "单笔交易金额范围0.01-99999";

    int SPECIALTY_INTRODUCE_MAX = 9999;
    int SPECIALTY_INTRODUCE_MIN = 0;
    String SPECIALTY_INTRODUCE_MESSAGE = "专业介绍范围0-9999";

    int URL_LENGTH_MAX = 9999;
    int URL_LENGTH_MIN = 0;
    String URL_LENGTH_MESSGAE = "url范围0-9999";

    //TODO 常量膨胀了，定义一些通用长度
    int MEDIUM_LENGTH_MAX = 200;
    
    int PASSWORD_LENGTH_MIN = 1;
    String PASSWORD_LENGTH_MESSAGE = "密码长度范围" + MEDIUM_LENGTH_MAX + "-" + PASSWORD_LENGTH_MIN;

    int VALIDATE_EMAIL_LENGTH = 6;
    String VALIDATE_EMAIL_LENGTH_MESSAGE = "短信验证码必须等于" + VALIDATE_EMAIL_LENGTH;

    int VALIDATE_CODE_LENGTH = 4;
    String VALIDATE_CODE_LENGTH_MESSAGE = "验证码长度必须等于" + VALIDATE_CODE_LENGTH;

}
