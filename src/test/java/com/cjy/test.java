package com.cjy;

import java.util.Scanner;

/****
 * @Author:cjy
 * @Description: com.cjy
 * @Date 2022/10/4 7:55
 *****/
public class test {
    public static void main(String[] args) {
//        char sex;
//        char sports;
//        char diet;
        float yoHeight = 0;
//        float faHeight;
//        float moHeight;
        System.out.println("输入你的sex回车结束,M/F:");
        Scanner sc = new Scanner(System.in);
        String sex = sc.nextLine();
        System.out.println("输入你父亲身高:");
        int faHeight = sc.nextInt();
        System.out.println("输入你母亲身高:");
        int moHeight = sc.nextInt();
        System.out.println("喜欢运动Y/N:");
        String sports = sc.next();
        System.out.println("良好饮食习惯？Y/N:");
        String diet = sc.next();
        if(sex.equals("M")) yoHeight= (float) ((faHeight+moHeight)*0.54);
        if (sex.equals("F"))yoHeight= (float) ((faHeight*0.923+moHeight)/2.0);
        if (sports.equals("Y")) yoHeight = (float) (yoHeight * (1 + 0.02));
        if (diet.equals("Y")) yoHeight= (float) (yoHeight*(1+0.015));

        System.out.println(yoHeight);

    }

}

class test01 {
    public static void main(String[] args) {
        double dis = 1e-0;
        double a = 3;
        double b = 4;
        System.out.println(Math.abs(a-b)>dis);

    }

}

