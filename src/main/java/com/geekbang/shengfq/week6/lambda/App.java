package com.geekbang.shengfq.week6.lambda;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App  {
	static List<Book> books = new ArrayList<>();
	static{
				books.add(new Book(7, "html", 44d, "编程语言", LocalDate.parse("2011-01-06")));
				books.add(new Book(8, "oracle", 150d, "数据库", LocalDate.parse("2013-08-09")));
				books.add(new Book(9, "mysql", 66d, "数据库", LocalDate.parse("2015-04-06")));
				books.add(new Book(10, "ssh", 70d, "编程语言", LocalDate.parse("2016-12-04")));
				books.add(new Book(11, "设计模式", 81d, "其他", LocalDate.parse("2017-04-06")));
				books.add(new Book(12, "重构", 62d, "其他", LocalDate.parse("2012-04-09")));
				books.add(new Book(13, "敏捷开发", 72d, "其他", LocalDate.parse("2016-09-07")));
				books.add(new Book(14, "从技术到管理", 42d, "其他", LocalDate.parse("2016-02-19")));
				books.add(new Book(15, "算法导论", 66d, "其他", LocalDate.parse("2010-05-08")));
				books.add(new Book(16, "oracle 12c", 150d, "数据库", LocalDate.parse("2017-05-08")));
				books.add(new Book(1, "tomcat", 70d, "服务器", LocalDate.parse("2014-05-17")));
				books.add(new Book(2, "jetty", 60d, "服务器", LocalDate.parse("2015-12-01")));
				books.add(new Book(3, "nginx", 65d, "服务器", LocalDate.parse("2016-10-17")));
				books.add(new Book(4, "java", 66d, "编程语言", LocalDate.parse("2011-04-09")));
				books.add(new Book(5, "ruby", 80d, "编程语言", LocalDate.parse("2013-05-09")));
				books.add(new Book(6, "php", 40d, "编程语言", LocalDate.parse("2014-08-06")));

	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args)  {

		String queryString = "itemId=1&userId=10000&type=20&token=111111111111&key=index";
		Map<String, String> bmap=Stream.of(queryString.split("&")).map(str -> str.split("=")).collect(Collectors.toMap(s->s[0], s->s[1]));
//		map.keySet().forEach(key -> {
//			System.out.println("key:"+key+"|value:"+map.get(key));
//		});
		System.out.println(bmap);


		/*
		 * 类型 				语法						对应的lambda表达式
		静态方法引用 		类名::staticMethod   (args) -> 类名.staticMethod(args)
		实例方法引用 		inst::instMethod     (args) -> inst.instMethod(args)
		对象方法引用 		类名::instMethod     (inst,args)  ->类名.instMethod(args)
		构造方法引用 		类名::new            (args) -> new 类名(args)
		 * */

		List<Integer> ids=books.stream().map(book->book.getId()).collect(Collectors.toList());
		System.out.println(ids);
					  ids=books.stream().map(Book::getId).collect(Collectors.toList());
		System.out.println(ids);

		String names=books.stream().map(Book::getName).sorted().collect(Collectors.joining(",","(",")"));
		System.out.println(names);

		Set<String> set=books.stream().map(Book::getType).collect(Collectors.toSet());
		System.out.println(set);

		Map<String, Object> map=new HashMap<String, Object>();
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		for (int i = 0; i < 5; i++) {
			map=new HashMap<String, Object>();
			map.put("age", 20+i);
			map.put("name", "aaa"+i);
			list.add(map);
		}

		System.out.println(list);
		List<String> nameList = list.stream()
				.map(tmap -> tmap.get("name") + "")
				.collect(Collectors.toList());

		List<Integer> ageList = list.stream().map(tmap -> {
			int age = (int) tmap.get("age");
			return age;
		}).collect(Collectors.toList());

		System.out.println(nameList);
		System.out.println(ageList);

		nameList = list
				.stream()
				.sorted(Comparator.comparing(tmap -> ((Map<String, String>) tmap).get("name")).reversed())
				.map(tmap -> tmap.get("name") + "")
				.collect(Collectors.toList());
		System.out.println(nameList);

		Book book=books.stream().min(Comparator.comparing(Book::getId)).get();
		System.out.println(book);


		boolean isMatch=list.stream().anyMatch(m->"aaa3".equals(m.get("name")));
		System.out.println(isMatch);

		ageList=list.stream().map(mm->Integer.parseInt(mm.get("age")+"")).collect(Collectors.toList());
		System.out.println(ageList);

		Object o = list.stream()
				.filter(fmap -> (Integer.parseInt(fmap.get("age") + "")) >= 22)
				.collect(Collectors.toList());
		System.out.println(o);

//		ReentrantLock lock=new ReentrantLock();
//		try {
//			lock.lock();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally{
//			lock.unlock();
//		}
}
}

