package com.qiandao8.qiandao8.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
    private String eventName;
    @Setter
    private String originator;
    @Setter
    private Date startTime;
    @Setter
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
    public static final int EFFECT_ROUTINE=1;


    /**
     *
     */
    @ToString.Exclude
    private List<BasicComponent> basicComponents = new ArrayList<>(16);
    @ToString.Exclude
    private List<ListComponent> listComponents = new ArrayList<>(8);

    /**
     * 需要的字符串格式: 姓名-aaa,手机
     * 用逗号分割每个对象
     * 若对象有- 则表示 title-remarks!content
     *
     * @param str
     */
    public void setBasicComponents(String str) {
        String[] textObj = str.split(",");
        for (String obj : textObj) {
            BasicComponent bc = new BasicComponent();
            // 存在内容
            if (obj.contains("!")) {
                String[] info_content = obj.split("!");
                bc.setContent(info_content[1]);
                //存在备注
                System.out.println(info_content[0] + "ifo222");
                if (info_content[0].contains("-")) {
                    String[] title_remarks = info_content[0].split("-");
                    bc.setTitle(title_remarks[0]);
                    bc.setRemarks(title_remarks[1]);
                } else {
                    bc.setTitle(info_content[0]);
                }
            } else {
                //不存在内容
                if (obj.contains("-")) {
                    String[] title_remarks = obj.split("-");
                    bc.setTitle(title_remarks[0]);
                    bc.setRemarks(title_remarks[1]);
                } else {
                    bc.setTitle(obj);
                }
            }
            this.basicComponents.add(bc);
        }
    }

    /**
     * 需要的字符串格式: 性别:男,女-年龄:20,30
     * 用-分割对象
     * title：options
     * 被选中的option后有!
     *
     * @param str
     */
    public void setListComponents(String str) {
        String[] objs = str.split("-");
        for (String obj : objs) {
            String[] title_opts = obj.split(":");
            String[] opts = title_opts[1].split(",");
            HashMap<String, Boolean> options = new HashMap<>();
            for (String opt : opts) {
                if (opt.endsWith("!")) {
                    options.put(opt.substring(0, opt.length() - 1), true);
                } else {
                    options.put(opt, false);
                }
            }
            this.listComponents.add(new ListComponent(title_opts[0], options));
        }

    }
}