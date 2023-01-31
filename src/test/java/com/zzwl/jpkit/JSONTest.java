package com.zzwl.jpkit;

import com.zzwl.jpkit.core.JSON;
import com.zzwl.jpkit.typeof.*;
import com.zzwl.jpkit.utils.StringUtil;
import com.zzwl.jpkit.vo.MySQL;
import com.zzwl.jpkit.vo.User;
import org.junit.Test;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class JSONTest {

    @Test
    public void start() {
        System.out.println("hello jpkit !!!");
    }

    @Test
    public void parse() {
        String json = "{\n" + "    \"title\":\"指挥平台\",\n" + "    \"englishTitle\": \"FLOODS DISASTERS DEFENSE DECISION COMMAND SYSTEM\",\n" + "    \"miniTitle\":\"防汛\",\n" + "    \"url\":\"/scfx/\",\n" + "    \"showCompanyInfo\": true,\n" + "    \"filingNo\":\"蜀ICP备2002458584号-2\",\n" + "    \"maintenanceUnit\":\"人类技术有限公司\",\n" + "    \"describe\":null,\n" + "    \"arr\":[],\n" + "    \"number\":[25,89.369,null, true,\"人类技术有限公司\",\"指挥平台\"],\n" + "    \"code\": 569\n," + "    \"time\": \"2023-1-9 18:00:00\"\n" + "}";
        JObject parse = (JObject) JSON.parse(json);
        JArray number = (JArray) parse.getValue().get("number");

        JDate date = new JDate(parse.getValue().get("time"), "yyyy-MM-dd HH:mm:SS");

        JShort code = new JShort(parse.getValue().get("code"));

        JChar jChar = new JChar(parse.getValue().get("title"));

        System.out.println(code.getValue());
        System.out.println(date.getValue());
        System.out.println(jChar.getValue());
        System.out.println(number);

        for (JBase jBase : number.getValue()) {
            System.out.println(jBase.getValue());
        }
    }

    @Test
    public void testString() throws NoSuchFieldException {
        Field field = User.class.getDeclaredField("admin");
        System.out.println(StringUtil.getMethodNameByFieldType(StringUtil.basicGetPrefix, field.getType(), field.getName()));
    }

    @Test
    public void testStringify() {
        Integer[] nums = new Integer[]{4545, 2121, 3636};
        String[] ss = new String[]{"zz", "xx", "ww"};
        User zzwl = new User(1L, "zzwl", 300, true, new Date(), nums, ss);
        long[] longs = new long[]{5164161651651165151L, 56156151655616556L, 165156516156156L};
        zzwl.setLongs(longs);
        List<Long> list = new ArrayList<>();
        list.add(1651465163113313131L);
        list.add(165146516423313131L);
        list.add(165146516453313131L);
        zzwl.setLongList(list);
        JSON.setLongToString(true);
        System.out.println(JSON.stringify(list).pretty());
        System.out.println("=================");
        String s = JSON.stringify(zzwl).terse();
        System.out.println(s);
        System.out.println(zzwl);

    }

    @Test
    public void testArray() {
        int[] ints = new int[]{589, 456, 89};
        System.out.println(JSON.stringify(ints).terse());
        System.out.println(JSON.stringify(ints).pretty());

        String[] strings = new String[]{"sgsgssg", "gsfsgdgsd", "sdgdsfgfdg", "sdgfdgfdgs", "sdfgfsdggf"};

        System.out.println(JSON.stringify(strings).terse());
        System.out.println(JSON.stringify(strings).pretty());

        char[] chars = new char[]{'f', 'g', 'y', 'u', 'p'};

        System.out.println(JSON.stringify(chars).terse());
        System.out.println(JSON.stringify(chars).pretty());

        byte[] shorts = new byte[]{123, 34, 24};

        System.out.println(JSON.stringify(shorts).terse());
        System.out.println(JSON.stringify(shorts).pretty());

        boolean[] booleans = new boolean[]{true, false, true, true, false};

        System.out.println(JSON.stringify(booleans).pretty());
        System.out.println(JSON.stringify(booleans).terse());

        System.out.println(JSON.stringify("oop").pretty());
    }

    @Test
    public void testCollection() {
        List<Integer> list = new ArrayList<>();

        list.add(456);
        list.add(234);
        list.add(789);
        list.add(123);

        System.out.println(JSON.stringify(list).terse());

        Map<String, Boolean> map = new HashMap<>();

        map.put("isAdmin", true);
        map.put("isPublish", false);
        map.put("isCopy", true);
        System.out.println(JSON.stringify(map).pretty(2));
    }

    @Test
    public void testListAndMap() {
        List<User> users = new ArrayList<>();
        String path = "src\\main\\resources\\db.json";

        users.add(new User(1004207420089456666L, "zzwl", 300, true, new Date(), new Integer[]{789, 526}, new String[]{"gg", "hh"}));
        users.add(new User(2L, "zzwl", 400, false, new Date(), new Integer[]{789, 526}, new String[]{"gg", "hh"}));
        users.add(new User(3L, "zzwl", 500, true, new Date(), new Integer[]{789, 526}, new String[]{"gg", "hh"}));
        users.add(new User(4L, "zzwl", 600, false, new Date(), new Integer[]{789, 526}, new String[]{"gg", "hh"}));
        users.add(new User(5L, "zzwl", 700, false, new Date(), new Integer[]{789, 526}, new String[]{"gg", "hh"}));

        JSON.setTabLength(1);
        JSON.setTabCharacter('\t');
        JSON.stringify(users).save(path, 1);

        System.out.println(JSON.stringify(users).terse(1));
        System.out.println(JSON.stringify(users).pretty(1));
        System.out.println("=================================================");
        System.out.println(JSON.stringify(users).pretty(3));
    }

    @Test
    public void testSave() {
        User user = new User(1L, "zzwl_plus", 400, true, new Date(), new Integer[]{789, 526}, new String[]{"gg", "hh"});
//        JSON.stringify(user).save("D:\\user\\backpackerxl\\jpkit\\src\\main\\resources\\db.json", false);


        String url = "https://www.baidu.com/sugrec?prod=pc_his&from=pc_web&json=1&sid=36547_37647_37556_38057_36920_37989_37920_38040_26350_22157_37881&hisdata=&_t=1674049868387&csor=0";
        String local = "src\\main\\resources\\db.json";

//        JBase net = (JBase) JSON.load(url);
        JBase net_local = (JBase) JSON.load(local);

//        System.out.println(JSON.stringify(net).pretty());
        System.out.println("===================================");
        String js = JSON.stringify(net_local).pretty();
        System.out.println(js);

        List<User> us = JSON.loadList(local, User.class);
        System.out.println(us);

    }

    @Test
    public void testDate() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(JDate.YYYY_MM_DD);
        Date parse = simpleDateFormat.parse("2023-1-27");
        System.out.println(parse);
    }


    @Test
    public void testObjectParse() {
        String json = "{\n" + "  \"id\": \"1\",\n" + "  \"username\": \"zzwl\",\n" + "  \"user_code\": 300,\n" + "  \"admin\": true,\n" + "  \"create_time\": \"2023-01-31\",\n" + "  \"nums\": [\n" + "    4545,\n" + "    2121,\n" + "    3636\n" + "  ],\n" + "  \"strings\": [\n" + "    \"zz\",\n" + "    \"xx\",\n" + "    \"ww\"\n" + "  ],\n" + "  \"ints\": null,\n" + "  \"longs\": [\n" + "    \"5164161651651165151\",\n" + "    \"56156151655616556\",\n" + "    \"165156516156156\"\n" + "  ],\n" + "  \"longList\": [\n" + "    \"1651465163113313131\",\n" + "    \"165146516423313131\",\n" + "    \"165146516453313131\"\n" + "  ]\n" + "}";
//        JObject parse = (JObject) JSON.parse(json);
//        System.out.println(parse);
        User user = JSON.parse(json, User.class);
        System.out.println(user);
    }

    @Test
    public void testArr() throws NoSuchFieldException {
        Field nums = User.class.getDeclaredField("nums");

        String typeName = nums.getType().getTypeName();
        String name = Integer[].class.getTypeName();
        String name_ = Integer.class.getTypeName();
        System.out.println(typeName + "===>" + name);
        System.out.println(name_);
    }

    @Test
    public void testParseList() {

        String list = "[\n" + "\t{\n" + "\t\t\"id\": \"1004207420089456666\",\n" + "\t\t\"username\": \"zzwl\",\n" + "\t\t\"user_code\": 300,\n" + "\t\t\"admin\": true,\n" + "\t\t\"create_time\": \"2023-01-31\",\n" + "\t\t\"nums\": [\n" + "\t\t\t789,\n" + "\t\t\t526\n" + "\t\t],\n" + "\t\t\"strings\": [\n" + "\t\t\t\"gg\",\n" + "\t\t\t\"hh\"\n" + "\t\t],\n" + "\t\t\"ints\": null,\n" + "\t\t\"longs\": null,\n" + "\t\t\"longList\": null\n" + "\t},\n" + "\t{\n" + "\t\t\"id\": \"2\",\n" + "\t\t\"username\": \"zzwl\",\n" + "\t\t\"user_code\": 400,\n" + "\t\t\"admin\": false,\n" + "\t\t\"create_time\": \"2023-01-31\",\n" + "\t\t\"nums\": [\n" + "\t\t\t789,\n" + "\t\t\t526\n" + "\t\t],\n" + "\t\t\"strings\": [\n" + "\t\t\t\"gg\",\n" + "\t\t\t\"hh\"\n" + "\t\t],\n" + "\t\t\"ints\": null,\n" + "\t\t\"longs\": null,\n" + "\t\t\"longList\": null\n" + "\t},\n" + "\t{\n" + "\t\t\"id\": \"3\",\n" + "\t\t\"username\": \"zzwl\",\n" + "\t\t\"user_code\": 500,\n" + "\t\t\"admin\": true,\n" + "\t\t\"create_time\": \"2023-01-31\",\n" + "\t\t\"nums\": [\n" + "\t\t\t789,\n" + "\t\t\t526\n" + "\t\t],\n" + "\t\t\"strings\": [\n" + "\t\t\t\"gg\",\n" + "\t\t\t\"hh\"\n" + "\t\t],\n" + "\t\t\"ints\": null,\n" + "\t\t\"longs\": null,\n" + "\t\t\"longList\": null\n" + "\t}\n" + "]";

        List<User> users = JSON.parseList(list, User.class);

        System.out.println(JSON.stringify(users).pretty());
    }

    @Test
    public void testSubStringify() {
        MySQL mySQL = new MySQL("mysql", "5.7.35", new BigDecimal("0.25689"));

        List<BigDecimal> bigDecimals = new ArrayList<>();
        bigDecimals.add(new BigDecimal("0.1"));
        bigDecimals.add(new BigDecimal("0.1566"));
        bigDecimals.add(new BigDecimal("0.2568"));
        bigDecimals.add(new BigDecimal("0.84894"));

        mySQL.setBigDecimals(bigDecimals);
        System.out.println(mySQL);
    }

    @Test
    public void testSubParse() {
        String json = "{\n" + "  \"server\": \"mysql\",\n" + "  \"version\": \"5.7.35\",\n" + "  \"bigDecimal\": 0.25689\n" + "}";
        MySQL parse = JSON.parse(json, MySQL.class);
        System.out.println(parse);
    }
}
