package com.jeecloud.common.util;

/**
 * 常量
 * @author dingping
 *
 */
public class Constant {
	/** 超级管理员ID */
	public static final int SUPER_ADMIN = 1;

	/**
	 * 菜单类型
	 */
    public enum MenuType {
        /**
         * 目录
         */
    	CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2),
        /**
         * 数据列
         */
    	COLUMN(3);

        private int value;

        MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
    
    /**
     * 定时任务状态
     */
    public enum ScheduleStatus {
        /**
         * 正常
         */
    	NORMAL(0),
        /**
         * 暂停
         */
    	PAUSE(1);

        private int value;

        ScheduleStatus(int value) {
            this.value = value;
        }
        
        public int getValue() {
            return value;
        }
    }

    /**
     * 云服务商
     */
    public enum CloudService {
        /**
         * 七牛云
         */
        QINIU(1),
        /**
         * 阿里云
         */
        ALIYUN(2),
        /**
         * 腾讯云
         */
        QCLOUD(3),
        /**
         * 华为云
         */
    	HWCLOUD(4);
    	
        private int value;

        CloudService(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
    public static final String KAPTCHA_SESSION_KEY = "KAPTCHA_SESSION_KEY";
    public static final String KAPTCHA_SESSION_DATE = "KAPTCHA_SESSION_DATE";
    public static final String KAPTCHA_SESSION_CONFIG_KEY = "kaptcha.session.key";
    public static final String KAPTCHA_SESSION_CONFIG_DATE = "kaptcha.session.date";
    public static final String KAPTCHA_BORDER = "kaptcha.border";
    public static final String KAPTCHA_BORDER_COLOR = "kaptcha.border.color";
    public static final String KAPTCHA_BORDER_THICKNESS = "kaptcha.border.thickness";
    public static final String KAPTCHA_NOISE_COLOR = "kaptcha.noise.color";
    public static final String KAPTCHA_NOISE_IMPL = "kaptcha.noise.impl";
    public static final String KAPTCHA_OBSCURIFICATOR_IMPL = "kaptcha.obscurificator.impl";
    public static final String KAPTCHA_PRODUCER_IMPL = "kaptcha.producer.impl";
    public static final String KAPTCHA_TEXTPRODUCER_IMPL = "kaptcha.textproducer.impl";
    public static final String KAPTCHA_TEXTPRODUCER_CHAR_STRING = "kaptcha.textproducer.char.string";
    public static final String KAPTCHA_TEXTPRODUCER_CHAR_LENGTH = "kaptcha.textproducer.char.length";
    public static final String KAPTCHA_TEXTPRODUCER_FONT_NAMES = "kaptcha.textproducer.font.names";
    public static final String KAPTCHA_TEXTPRODUCER_FONT_COLOR = "kaptcha.textproducer.font.color";
    public static final String KAPTCHA_TEXTPRODUCER_FONT_SIZE = "kaptcha.textproducer.font.size";
    public static final String KAPTCHA_TEXTPRODUCER_CHAR_SPACE = "kaptcha.textproducer.char.space";
    public static final String KAPTCHA_WORDRENDERER_IMPL = "kaptcha.word.impl";
    public static final String KAPTCHA_BACKGROUND_IMPL = "kaptcha.background.impl";
    public static final String KAPTCHA_BACKGROUND_CLR_FROM = "kaptcha.background.clear.from";
    public static final String KAPTCHA_BACKGROUND_CLR_TO = "kaptcha.background.clear.to";
    public static final String KAPTCHA_IMAGE_WIDTH = "kaptcha.image.width";
    public static final String KAPTCHA_IMAGE_HEIGHT = "kaptcha.image.height";
}
