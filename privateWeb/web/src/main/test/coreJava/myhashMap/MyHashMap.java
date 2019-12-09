package coreJava.myhashMap;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @Description TODO
 * @Author 赵赫智
 * @Date 2019/5/25 11:39
 **/
public class MyHashMap {
    private int size = 8;
    private int number = 0;// 存储的节点的个数
    private ArrayList<LinkedList> array_head = new ArrayList<LinkedList>(size);
    public MyHashMap() {
        super();
        for (int i = 0; i < size; i++) {
            LinkedList list = new LinkedList();// 哈希数组中初始化存储的为空链表头
            array_head.add(list);// 初始化的时候就将空节点头添加到数组中去
        }

    }
    /**
     * 根据 键值对 生成节点 将节点放入哈希表中
     *
     * @param key
     *            键
     * @param value
     *            值
     */
    public void put(Object key, Object value) {
        if (containsKey(key) == true) {// 判断是否已经存在该key值,如果存在，直接替换该value值
            replace(key, value);
        } else {// 不存在，创建一个新的key值
            if (number / size == 10) {
                rehash();
            }
            number++;
            Node node = new Node(key, value);
            int code = hashcode(key.toString());// 得到哈希码
            int index = locate(code);// 得到该哈希码在对应哈希数组中的位置

            // 找到对应位置的链表头
            LinkedList<Node> list_head = array_head.get(index);
            list_head.add(node);// 将节点放进链表中
        }
    }

    /**
     * 打印哈希表
     */
    public void show() {
        System.out.println("打印哈希表");
        for (int i = 0; i < size; i++) {
            LinkedList link_head = array_head.get(i);// 得到链表头
            System.out.println("链表：" + i);
            for (int j = 0; j < link_head.size(); j++) {
                Node node = (Node) link_head.get(j);// 打印每个节点
                while (node != null) {
                    // 打印出没个节点对应的键值对
                    System.out.print("节点" + (j + 1) + ":" + "<" + node.getKey() + "," + node.getValue() + ">" + "  ");
                    node = node.getNext();

                }

            }
            System.out.println();
        }

    }

    /**
     * 根据键得到值
     */
    public Object getValue(Object key) {
        // 根据key值找到数组对应位置
        int code = hashcode(key.toString());
        int index = locate(code);
        LinkedList list = array_head.get(index);
        // 从头遍历，找到与键key对应节点的value值进行输出
        for (int i = 0; i < list.size(); i++) {
            Node node = (Node) list.get(i);
            while (node != null) {
                if (node.getKey().equals(key)) {
                    return node.getValue();
                }
                node = node.getNext();
            }

        }
        return null;

    }

    /**
     * 移除节点
     *
     * @param key
     * @return
     */
    public boolean remove(Object key) {
        number--;
        int code = hashcode(key.toString());
        int index = locate(code);
        LinkedList list = array_head.get(index);

        for (int i = 0; i < list.size(); i++) {
            Node node = (Node) list.get(i);
            while (node != null) {
                if (node.getKey().equals(key)) {
                    list.remove(i);
                    return true;
                }
                node = node.getNext();
            }
        }
        return false;
    }

    /**
     * 替换key键处的value值
     *
     * @param key
     * @param value
     * @return
     */
    public boolean replace(Object key, Object value) {
        // 根据key值找到数组对应位置
        int code = hashcode(key.toString());
        int index = locate(code);
        LinkedList list = array_head.get(index);
        for (int i = 0; i < list.size(); i++) {
            Node node = (Node) list.get(i);
            if (node.getKey().equals(key)) {
                node.setValue(value);
                return true;
            }
        }

        return false;
    }

    /**
     * 清空方法
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            LinkedList list = array_head.get(i);
            list.clear();
        }
        number = 0;
    }

    /**
     * 哈希表中含key键，返回true
     *
     * @param key
     * @return
     */

    public boolean containsKey(Object key) {
        // 根据key值找到数组对应位置
        int code = hashcode(key.toString());
        int index = locate(code);
        LinkedList list = array_head.get(index);
        for (int i = 0; i < list.size(); i++) {
            Node node = (Node) list.get(i);
            if (node.getKey().equals(key)) {
                return true;
            }
            node = node.getNext();
        }

        return false;
    }

    /**
     * 哈希表中含value值，返回true
     *
     * @param value
     * @return
     */
    public boolean containsValue(Object value) {
        // 找到该对应位置的链表
        for (int i = 0; i < size; i++) {
            LinkedList list = array_head.get(i);
            // 从头遍历，找到与键key对应节点的value值进行输出
            for (int j = 0; j < list.size(); j++) {
                Node node = (Node) list.get(j);
                while (node != null) {
                    if (node.getValue().equals(value)) {
                        return true;
                    }
                    node = node.getNext();
                }

            }
        }
        return false;
    }

    private void rehash() {

    }

    /**
     * 计算字符串的哈希码 ASCII码相加
     *
     * @param s
     * @return
     */
    public int hashcode(String s) {
        int k = 0;
        for (int i = 0; i < s.length(); i++) {
            k += s.charAt(i);
        }
        return k;
    }

    /**
     * 得到哈希码对应在数组中的位置
     *
     * @param k
     * @return
     */
    public int locate(int k) {
        int m = k % size;
        return m;
    }

    /**
     * 返回存贮节点的个数
     */
    public int size() {
        return number;
    }
}
