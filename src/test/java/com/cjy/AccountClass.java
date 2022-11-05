package com.cjy;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/****
 * @Author:cjy
 * @Description: com.cjy
 * @Date
 *****/
public class AccountClass {
    public int accountBalance=1000;
}
class AccountThread extends Thread{
//    private Lock lock = new ReentrantLock();
    private final AccountClass AccountBalance;
    private final String method;
//    private Object lock;


    public AccountThread(AccountClass balance,String name){
        this.AccountBalance=balance;
        this.method=name;


    }
    @Override
    public void run() {

        /*while(AccountBalance.accountBalance > 0) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            synchronized (AccountBalance){
            //原子操作:不会被打断地的操作。(就是做到互斥和可见性)
            AccountBalance.accountBalance-=100;
            System.out.println("用户通过："+method+"取走了1000， " + "余额为："+AccountBalance.accountBalance);
        }*/

//        }

        //synchronized () {}保障线程安全
        while (true) {
            synchronized (AccountBalance) {
                //原子操作
                if(AccountBalance.accountBalance>0){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    AccountBalance.accountBalance -= 100;
                    System.out.println("用户通过：" + method + "取走了100， " + "余额为：" + AccountBalance.accountBalance);
                }

            }}

        //手动解锁
        /*while (true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                lock.lock();
                if (AccountBalance.accountBalance > 0) {

                    AccountBalance.accountBalance -= 100;
                    System.out.println("用户通过：" +method+ Thread.currentThread().getName() + "取走了100， " + "余额为：" + AccountBalance.accountBalance);
                }
            } finally {
                lock.unlock();
            }

        }*/


    }

}
class ManageAccount {
    public static void main(String[] args) {
        AccountClass AccountBalance=new AccountClass();
        new AccountThread(AccountBalance,"APP").start();
        new AccountThread(AccountBalance,"自助取款机").start();
        new AccountThread(AccountBalance,"柜台办理").start();
    }
}


