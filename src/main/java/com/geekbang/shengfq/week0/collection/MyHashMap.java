package com.geekbang.shengfq.week0.collection;
/**
 * 泛型接口实现
 * 自定义hashmap
 * @author sheng
 *未实现自动扩容
 * */
public class MyHashMap<K,V> implements MyMap<K,V> {
    //默认容量
    private static int defaultLength = 16;
    //默认加载因子(偏移量)
    private static double defualtLoaderFactor = 0.75;
    //最大容量

    //当前使用的存储结构是数组
    private Entry<K,V> table[] = null;
    private int size = 0;

    public MyHashMap() {
        this(defaultLength,defualtLoaderFactor);
    }

    public MyHashMap(int length,double loaderFactor) {
        defaultLength = length;
        defualtLoaderFactor = loaderFactor;
        table = new Entry[defaultLength];
    }

    /**
     * hash算法,计算桶位索引值
     * 比如:A通过hash得到索引号1
     * B通过hash得到索引号2
     * */
    private int hash(K k) {
        int m = defaultLength;
        int i = k.hashCode() % m;
        return i > 0 ? i : -i;
    }
    /**
     * 存入Map结构
     * */
    @Override
    public V put(K key, V value) {
        int index = hash(key);

        Entry<K,V> entry = table[index];
        if(entry == null) {
            table[index] = new Entry(key,value,null);
        }else {
            table[index] = new Entry(key,value,entry);
            System.out.println("oldVlaue=" + table[index].next.getValue());
        }
        size ++;
        return table[index].getValue();
    }
    /**
     * 从map中取数据
     * */
    @Override
    public V get(K key) {
        int index = hash(key);
        if(table[index] == null) {
            return  null;
        }
        return find(table[index],key);
    }
    /**
     * 从map中查找Entry
     * */
    private V find(Entry<K, V> entry, K key) {

        if(key == entry.getKey()||key.equals(entry.getKey())) {
            if(entry.next != null) {
                System.out.println("oldValue1=" + entry.next.getValue());
            }
            return entry.getValue();
        }else {
            //不相等的时候，就直接递归去取下一个值
            if(entry.next != null) {
                System.out.println("oldValue2=" + entry.next.getValue());
                return find(entry.next,key);
            }
        }
        return null;
    }
    /**
     * 返回map的大小
     * */
    @Override
    public int size() {
        return size;
    }
    /**
     * 实现map中的键值对对象结构
     * */
    class Entry<K,V> implements MyMap.Entry<K,V> {
        K k;
        V v;

        Entry<K,V> next;

        public Entry(K k,V v,Entry<K,V> next) {
            this.k = k;
            this.v = v;
            this.next = next;
        }

        @Override
        public K getKey() {
            return k;
        }

        @Override
        public V getValue() {
            return v;
        }
    }
}
