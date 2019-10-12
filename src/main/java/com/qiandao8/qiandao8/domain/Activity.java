package com.qiandao8.qiandao8.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Getter
@ToString
public class Activity {
    @Setter
    private Long id;
    @Setter
    private String activityName;
    @Setter
    private Long originatorId;
    @Setter
    private String originator;
    @Setter
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @Setter
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    @Setter
    private Integer status;
    @Setter
    private Integer type;
    @Setter
    private Integer participantsNums;
    @Setter
    private Date createTime;
    @Setter
    private Date updateTime;
    @Setter
    private String basicSelc;
    @Setter
    private String listSelc;

    /**
     * 用于标记type：一次性的活动
     */
    public static final int EFFECT_ONCE = 0;

    /**
     * 用于标记type：周期生效的活动
     */
    public static final int EFFECT_ROUTINE = 1;


    /**
     * 用于将特定字符串解析为json所用到的中间模型
     */
    @ToString.Exclude
    @JsonIgnore
    private List<BasicComponent> basicComponents = new ArrayList<>(16);
    @ToString.Exclude
    @JsonIgnore
    private List<ListComponent> listComponents = new ArrayList<>(8);

    /**
     * 表示活动状态处于正常 states
     */
    public static final int STATES_NORMAL = 0;

    /**
     * 表示活动结束了
     */
    public static final int STATES_FINISH = 1;

    /**
     * 表示活动被删除了
     */
    public static final int STATES_REMOVED = 2;

    /**
     * 表示活动因为各种原因，被管理员冻结了
     */
    public static final int STATES_FROZEN = 10;


    /**
     * 需要的字符串格式: 姓名-aaa,手机
     * 用逗号分割每个对象
     *
     * @param str
     */
    public void setBasicComponents(String str) {
        if (str.equals("")) {
            return;
        }
        String[] textObj = str.split(BasicComponent.OBJECT_SEPARATOR);
        for (String obj : textObj) {
            BasicComponent bc = new BasicComponent();
            // 存在备注
            if (obj.contains(BasicComponent.TITLE_REMARK_SEPARATOR)) {
                String[] title_remarks = obj.split(BasicComponent.TITLE_REMARK_SEPARATOR);
                bc.setTitle(title_remarks[0]);
                bc.setRemarks(title_remarks[1]);
            } else {
                bc.setTitle(obj);
            }
            this.basicComponents.add(bc);
        }
    }

    /**
     * 需要的字符串格式: 性别:男,女-年龄:20,30
     * 用-分割对象
     * title：options
     *
     * @param str
     */
    public void setListComponents(String str) {
        if (str.equals("")) {
            return;
        }
        String[] objs = str.split(ListComponent.OBJECT_SEPARATOR);
        for (String obj : objs) {
            String[] title_opts = obj.split(ListComponent.TITLE_OPTS_SEPARATOR);
            String[] opts = title_opts[1].split(ListComponent.OPTION_SEPARATOR);
            HashMap<String, Boolean> options = new HashMap<>();
            for (String opt : opts) {
                options.put(opt, false);
            }
            this.listComponents.add(new ListComponent(title_opts[0], options));
        }

    }
}
// * 若对象有- 则表示 title-remarks!content
//      * 被选中的option后有!
//    public void setBasicComponents(String str) {
//        String[] textObj = str.split(",");
//        for (String obj : textObj) {
//            BasicComponent bc = new BasicComponent();
//            // 存在内容
//            if (obj.contains("!")) {
//                String[] info_content = obj.split("!");
//                bc.setContent(info_content[1]);
//                //存在备注
//                System.out.println(info_content[0] + "ifo222");
//                if (info_content[0].contains("-")) {
//                    String[] title_remarks = info_content[0].split("-");
//                    bc.setTitle(title_remarks[0]);
//                    bc.setRemarks(title_remarks[1]);
//                } else {
//                    bc.setTitle(info_content[0]);
//                }
//            } else {
//                //不存在内容
//                if (obj.contains("-")) {
//                    String[] title_remarks = obj.split("-");
//                    bc.setTitle(title_remarks[0]);
//                    bc.setRemarks(title_remarks[1]);
//                } else {
//                    bc.setTitle(obj);
//                }
//            }
//            this.basicComponents.add(bc);
//        }
//    }
//
//    public void setListComponents(String str) {
//        String[] objs = str.split("-");
//        for (String obj : objs) {
//            String[] title_opts = obj.split(":");
//            String[] opts = title_opts[1].split(",");
//            HashMap<String, Boolean> options = new HashMap<>();
//            for (String opt : opts) {
//                if (opt.endsWith("!")) {
//                    options.put(opt.substring(0, opt.length() - 1), true);
//                } else {
//                    options.put(opt, false);
//                }
//            }
//            this.listComponents.add(new ListComponent(title_opts[0], options));
//        }
//
//    }