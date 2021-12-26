package uni.labs.testlabs.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TreeNode<T> { //var4
    private static final int CAPACITY = 8;
    private final List<TreeNode<T>> children = new ArrayList<>(CAPACITY);
    private T data;
    private TreeNode<T> parent = null;

    public TreeNode(T data) {
        this.data = data;
    }

    public static <N> List<TreeNode<N>> getAllElements(TreeNode<N> node) {
        List<TreeNode<N>> nodeLists = node.getChildren().stream()
                .map(TreeNode::getAllElements)
                .flatMap(List::stream)
                .collect(Collectors.toList());

        nodeLists.add(node);
        return nodeLists;
    }

    public static <N> String printTree(TreeNode<N> node, String appender) {
        String nodeValues = node.getChildren().stream()
                .map(each -> printTree(each, appender + appender))
                .collect(Collectors.joining("\n"));

        return String.format("%s%s\n%s", appender, node.getData(), nodeValues);
    }

    public static <N> TreeNode<N> findElement(TreeNode<N> node, N element) {
        if (node.getData().equals(element)) {
            return node;
        }

        for (TreeNode<N> children : node.getChildren()) {
            TreeNode<N> foundElement = findElement(children, element);
            if (foundElement != null) {
                return foundElement;
            }
        }
        return null;
    }

    public TreeNode<T> addChild(TreeNode<T> child) {
        if (this.children.size() >= CAPACITY) {
            return this.children.get(0).addChild(child);
        }
        child.setParent(this);
        this.children.add(child);
        return child;
    }

    public List<TreeNode<T>> getChildren() {
        return children;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public TreeNode<T> getParent() {
        return parent;
    }

    private void setParent(TreeNode<T> parent) {
        this.parent = parent;
    }

    public void deleteNode() {
        if (parent != null) {
            if (children.size() + (parent.getChildren().size() - 1) > 8) {
                throw new IllegalStateException("Can not delete node because its parent node is full");
            }

            int index = this.parent.getChildren().indexOf(this);
            this.parent.getChildren().remove(this);
            for (TreeNode<T> each : getChildren()) {
                each.setParent(this.parent);
            }
            this.parent.getChildren().addAll(index, this.getChildren());
        } else {
            deleteRootNode();
        }
        this.getChildren().clear();
    }

    private TreeNode<T> deleteRootNode() {
        if (parent != null) {
            throw new IllegalStateException("Can not call method on a non root element");
        }
        TreeNode<T> newParent = null;
        if (!getChildren().isEmpty()) {
            newParent = getChildren().get(0);
            newParent.setParent(null);
            getChildren().remove(0);
            for (TreeNode<T> each : getChildren()) {
                each.setParent(newParent);
            }
            newParent.getChildren().addAll(getChildren());
        }
        this.getChildren().clear();
        return newParent;
    }

}
