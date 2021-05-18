package com.geekbang.shengfq.week0.design;

import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @program com.example.network.common
 * @description 树型 数据结构 获取所有节点的所有子节点
 * @auther Mr.Xiong
 * @create 2021-05-13 00:16:27
 */
public class TreeNode {
    private String id;
    private List<TreeNode> children;

    public TreeNode(String id, List<TreeNode> children) {
        this.id = id;
        this.children = children;
    }

    public void addOne(TreeNode treeNode) {
        children.add(treeNode);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    public static void main(String[] args) {
        TreeNode node1a = new TreeNode("1a", new ArrayList<>());
        TreeNode node1b = new TreeNode("1b", new ArrayList<>());
        TreeNode node1b2a = new TreeNode("1b2a", new ArrayList<>());
        TreeNode node1b2b = new TreeNode("1b2b", new ArrayList<>());
        node1b.addOne(node1b2a);
        node1b.addOne(node1b2b);
        TreeNode node1c = new TreeNode("1c", new ArrayList<>());
        TreeNode node2c = new TreeNode("2c", new ArrayList<>());
        TreeNode node3c = new TreeNode("3c", new ArrayList<>());
        TreeNode node4c = new TreeNode("4c", new ArrayList<>());
        TreeNode node5c = new TreeNode("5c", new ArrayList<>());
        node1c.addOne(node2c);
        node2c.addOne(node3c);
        node3c.addOne(node4c);
        node4c.addOne(node5c);

        //模拟原始数据
        List<TreeNode> treeNodeList = new ArrayList<>();
        treeNodeList.add(node1a);
        treeNodeList.add(node1b);
        treeNodeList.add(node1c);

        //创建Map集合，存储所有节点的子节点映射
        Map<String, List<TreeNode>> allRelationMap = new LinkedHashMap<>();

        //遍历模拟的数据
        treeNodeList.forEach(n -> {
            //创建List集合，存储父节点的所有子节点
            List<TreeNode> childNodeList = new ArrayList<>();
            //创建Map集合，存放一条原始节点的数据n的每个节点以及它所有子节点的映射关系
            Map<String, List<TreeNode>> childNodeMap = new LinkedHashMap<>();
            //提取原始数据n的每个节点以及它所有子节点的映射关系
            fetchParentNodeAllChild(n, childNodeList, childNodeMap);
            //将原始数据n的每个节点以及它所有子节点的映射关系，存入总集合中
            allRelationMap.putAll(childNodeMap);
        });

        //打印结果
        for (Map.Entry<String, List<TreeNode>> entry : allRelationMap.entrySet()) {
            StringBuffer childrens = new StringBuffer();
            childrens.append("[");
            entry.getValue().forEach(a -> {
                childrens.append(a.getId()).append(",");
            });
            childrens.append("]");
            System.out.println(entry.getKey() + " : " + childrens.toString().replace(",]", "]"));
        }

    }

    private static void fetchParentNodeAllChild(TreeNode parentNode, List<TreeNode> childNodeList, Map<String, List<TreeNode>> childNodeMap) {
        //当子节点为空时，则把父节点parentNode作为第一个子节点
        if (CollectionUtils.isEmpty(childNodeList)) {
            childNodeList.add(parentNode);
        }
        //当父节点的子节点为空，说明子节点是它自己;不为空，继续递归查询下一级子节点
        if (!CollectionUtils.isEmpty(parentNode.getChildren())) {
            //当有子节点，创建List集合，存储新增加的节点，
            List<TreeNode> newAddChildNodeList = new ArrayList<>();
            parentNode.getChildren().forEach(m -> {
                //整理m节点的下一级子节点
                newAddChildNodeList.add(m);
                //递归提取父节点的所有子节点
                fetchParentNodeAllChild(m, newAddChildNodeList, childNodeMap);
            });
            //合并已有子节点和新增的子节点
            childNodeList.addAll(newAddChildNodeList);
            //将父节点及其所有子节点映射，存入到映射表
            childNodeToMap(parentNode, childNodeList, childNodeMap);
        } else {
            //子节点只是自己
            List<TreeNode> ownNodeList = new ArrayList<>();
            ownNodeList.add(parentNode);
            childNodeToMap(parentNode, ownNodeList, childNodeMap);
        }
    }

    private static void childNodeToMap(TreeNode parentNode, List<TreeNode> childNodeList, Map<String, List<TreeNode>> childNodeMap) {
        if (childNodeMap.containsKey(parentNode.getId())) {
            childNodeMap.get(parentNode.getId()).addAll(childNodeList);
        } else {
            childNodeMap.put(parentNode.getId(), childNodeList);
        }
    }
}
