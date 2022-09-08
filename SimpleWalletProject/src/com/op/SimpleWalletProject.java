package com.op;

import java.util.Scanner;

class Bank {
	static int total = 10000;

	static synchronized void withdrawn(String name, int withdrawal) {
		if (total >= withdrawal) {
			System.out.println("-------------------------------------");
			System.out.println(name + " withdrawn " + withdrawal);
			total = total - withdrawal;
			System.out.println("Balance After withdrawl " + total);
			System.out.println("-------------------------------------");
			try {
				Thread.sleep(1000);

			} catch (InterruptedException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else {
			System.out.println("-------------------------------------");
			System.out.println("You can not withdrawal " + withdrawal);
			System.out.println("Your Balance is " + total);
			System.out.println("--------------------------------------------------------------------------");

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("-------------------------------------");
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

	static synchronized void deposit(String name, int deposit) {
		System.out.println("--------------------------------------------------------------------------");
		System.out.println(name + " deposited " + deposit);
		total = total + deposit;
		System.out.println("Balance after deposit " + total);
		System.out.println("-------------------------------------");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("-------------------------------------");
		}
	}
}

class ThreadWithdrawal extends Thread {

	Bank object;
	String name;
	int rupee;

	public ThreadWithdrawal(Bank ob, String name, int money) {
		// TODO Auto-generated constructor stub
		this.object = ob;
		this.name = name;
		this.rupee = money;
	}

	@Override
	public void run() {
		object.withdrawn(name, rupee);
	}
}

class ThreadDeposit extends Thread {
	Bank object;
	String name;
	int rupee;

	public ThreadDeposit(Bank ob, String name, int money) {
		// TODO Auto-generated constructor stub
		this.object = ob;
		this.name = name;
		this.rupee = money;
	}

	@Override
	public void run() {
		object.deposit(name, rupee);
	}
}

public class SimpleWalletProject {
	public static void main(String[] args) throws InterruptedException {
		Bank obj = new Bank();
		Scanner sc = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		int ch;
		do {
			System.out.println("1.current balance");
			System.out.println("2.add money");
			System.out.println("3.withdraw money");
			System.out.println("Enter Your number of choice :");
			ch = sc.nextInt();
			switch (ch) {
			case 1:
				System.out.println("-------------------------------------");
				System.out.println("Your Current balance is " + obj.total);
				System.out.println("-------------------------------------");
				break;
			case 2:
				System.out.println("-------------------------------------");
				System.out.print("Enter Your Name:");
				String name = sc2.next();
				System.out.print("Enter add Money:");
				int add = sc2.nextInt();
				ThreadDeposit t1 = new ThreadDeposit(obj, name, add);
				t1.start();
				System.out.println("-------------------------------------");
				break;
			case 3:
				System.out.println("-------------------------------------");
				System.out.print("Enter your name: ");
				String name2 = sc2.next();
				System.out.print("Enter withdraw Money: ");
				int withdraw = sc2.nextInt();
				ThreadWithdrawal t2 = new ThreadWithdrawal(obj, name2, withdraw);
				t2.start();
				System.out.println("-------------------------------------");
				break;
			default:
				System.out.println("-------------------------------------");
				System.out.println("Enter Your Valid Number");
				break;
			}
		} while (ch != 0);
	}

}
