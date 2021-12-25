package uni.labs.testlabs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import uni.labs.testlabs.service.TreeNode;

import java.util.List;

@SpringBootTest
class ThirdLabVar4Test {

	@Test
	public void shouldAddNewElement() {
		// given
		TreeNode<String> root = new TreeNode<>("1");

		// when
		TreeNode<String> node = root.addChild(new TreeNode<>("1"));

		// then
		Assertions.assertNotNull(node);
		Assertions.assertEquals(root, node.getParent());
	}

	@Test
	public void shouldAddNewElementToFullNode() {
		// given
		TreeNode<String> root = new TreeNode<>("1");
		TreeNode<String> node1 = root.addChild(new TreeNode<>("1"));
		TreeNode<String> node2 = root.addChild(new TreeNode<>("2"));
		TreeNode<String> node3 = root.addChild(new TreeNode<>("3"));
		TreeNode<String> node4 = root.addChild(new TreeNode<>("4"));
		TreeNode<String> node5 = root.addChild(new TreeNode<>("5"));
		TreeNode<String> node6 = root.addChild(new TreeNode<>("6"));
		TreeNode<String> node7 = root.addChild(new TreeNode<>("7"));
		TreeNode<String> node8 = root.addChild(new TreeNode<>("8"));

		// when
		TreeNode<String> node = root.addChild(new TreeNode<>("81"));

		// then
		Assertions.assertNotNull(node);
		Assertions.assertEquals(node1, node.getParent());
	}

	@Test
	public void shouldDeleteLeafWithFullParentElement() {
		// given
		TreeNode<String> root = new TreeNode<>("1");
		TreeNode<String> node1 = root.addChild(new TreeNode<>("1"));
		TreeNode<String> node11 = node1.addChild(new TreeNode<>("11"));
		TreeNode<String> node2 = root.addChild(new TreeNode<>("2"));
		TreeNode<String> node3 = root.addChild(new TreeNode<>("3"));
		TreeNode<String> node4 = root.addChild(new TreeNode<>("4"));
		TreeNode<String> node5 = root.addChild(new TreeNode<>("5"));
		TreeNode<String> node6 = root.addChild(new TreeNode<>("6"));
		TreeNode<String> node7 = root.addChild(new TreeNode<>("7"));
		TreeNode<String> node8 = root.addChild(new TreeNode<>("8"));

		// when
		node11.deleteNode();

		// then
		Assertions.assertEquals(0, node1.getChildren().size());
	}

	@Test
	public void shouldDeleteNodeElementWithSingleChildAndFullParent() {
		// given
		TreeNode<String> root = new TreeNode<>("1");
		TreeNode<String> node1 = root.addChild(new TreeNode<>("1"));
		TreeNode<String> node11 = node1.addChild(new TreeNode<>("11"));
		TreeNode<String> node2 = root.addChild(new TreeNode<>("2"));
		TreeNode<String> node3 = root.addChild(new TreeNode<>("3"));
		TreeNode<String> node4 = root.addChild(new TreeNode<>("4"));
		TreeNode<String> node5 = root.addChild(new TreeNode<>("5"));
		TreeNode<String> node6 = root.addChild(new TreeNode<>("6"));
		TreeNode<String> node7 = root.addChild(new TreeNode<>("7"));
		TreeNode<String> node8 = root.addChild(new TreeNode<>("8"));

		// when
		node1.deleteNode();

		// then
		Assertions.assertEquals(root, node11.getParent());
	}

	@Test
	public void shouldDeleteNodeElementWithMultipleChildrenAndFullParent() {
		// given
		TreeNode<String> root = new TreeNode<>("1");
		TreeNode<String> node1 = root.addChild(new TreeNode<>("1"));
		TreeNode<String> node11 = node1.addChild(new TreeNode<>("11"));
		TreeNode<String> node12 = node1.addChild(new TreeNode<>("12"));
		TreeNode<String> node2 = root.addChild(new TreeNode<>("2"));
		TreeNode<String> node3 = root.addChild(new TreeNode<>("3"));
		TreeNode<String> node4 = root.addChild(new TreeNode<>("4"));
		TreeNode<String> node5 = root.addChild(new TreeNode<>("5"));
		TreeNode<String> node6 = root.addChild(new TreeNode<>("6"));
		TreeNode<String> node7 = root.addChild(new TreeNode<>("7"));
		TreeNode<String> node8 = root.addChild(new TreeNode<>("8"));

		// when
		// then
		Assertions.assertThrows(IllegalStateException.class, node1::deleteNode);
	}

	@Test
	public void shouldDeleteLeafElementWithNotFullParent() {
		// given
		TreeNode<String> root = new TreeNode<>("1");
		TreeNode<String> node1 = root.addChild(new TreeNode<>("1"));
		TreeNode<String> node11 = node1.addChild(new TreeNode<>("11"));
		TreeNode<String> node2 = root.addChild(new TreeNode<>("2"));
		TreeNode<String> node3 = root.addChild(new TreeNode<>("3"));
		TreeNode<String> node4 = root.addChild(new TreeNode<>("4"));
		TreeNode<String> node5 = root.addChild(new TreeNode<>("5"));
		TreeNode<String> node6 = root.addChild(new TreeNode<>("6"));
		TreeNode<String> node7 = root.addChild(new TreeNode<>("7"));

		// when
		node11.deleteNode();

		// then
		Assertions.assertEquals(0, node1.getChildren().size());
	}

	@Test
	public void shouldFindExistingElement() {
		// given
		TreeNode<String> root = new TreeNode<>("1");
		TreeNode<String> node1 = root.addChild(new TreeNode<>("1"));
		TreeNode<String> node11 = node1.addChild(new TreeNode<>("11"));
		TreeNode<String> node2 = root.addChild(new TreeNode<>("2"));
		TreeNode<String> node3 = root.addChild(new TreeNode<>("3"));
		TreeNode<String> node4 = root.addChild(new TreeNode<>("4"));
		TreeNode<String> node5 = root.addChild(new TreeNode<>("5"));
		TreeNode<String> node6 = root.addChild(new TreeNode<>("6"));
		TreeNode<String> node7 = root.addChild(new TreeNode<>("7"));
		TreeNode<String> node8 = root.addChild(new TreeNode<>("8"));

		// when
		TreeNode<String> element = TreeNode.findElement(root, "11");

		// then
		Assertions.assertNotNull(element);
	}

	@Test
	public void shouldFindNotExistingElement() {
		// given
		TreeNode<String> root = new TreeNode<>("1");
		TreeNode<String> node1 = root.addChild(new TreeNode<>("1"));
		TreeNode<String> node11 = node1.addChild(new TreeNode<>("11"));
		TreeNode<String> node2 = root.addChild(new TreeNode<>("2"));
		TreeNode<String> node3 = root.addChild(new TreeNode<>("3"));
		TreeNode<String> node4 = root.addChild(new TreeNode<>("4"));
		TreeNode<String> node5 = root.addChild(new TreeNode<>("5"));
		TreeNode<String> node6 = root.addChild(new TreeNode<>("6"));
		TreeNode<String> node7 = root.addChild(new TreeNode<>("7"));
		TreeNode<String> node8 = root.addChild(new TreeNode<>("8"));

		// when
		TreeNode<String> element = TreeNode.findElement(root, "111");

		// then
		Assertions.assertNull(element);
	}

	@Test
	public void shouldPrintAllElements() {
		//given
		TreeNode<String> root = new TreeNode<>("root");
		TreeNode<String> node1 = root.addChild(new TreeNode<>("1"));
		TreeNode<String> node11 = node1.addChild(new TreeNode<>("11"));
		TreeNode<String> node2 = root.addChild(new TreeNode<>("2"));
		TreeNode<String> node3 = root.addChild(new TreeNode<>("3"));
		TreeNode<String> node4 = root.addChild(new TreeNode<>("4"));
		TreeNode<String> node5 = root.addChild(new TreeNode<>("5"));
		TreeNode<String> node6 = root.addChild(new TreeNode<>("6"));
		TreeNode<String> node7 = root.addChild(new TreeNode<>("7"));
		TreeNode<String> node8 = root.addChild(new TreeNode<>("8"));

		// when
		String elements = TreeNode.printTree(root, " ");

		// then
		Assertions.assertEquals(" root\n" +
				"  1\n" +
				"    11\n" +
				"\n" +
				"  2\n" +
				"\n" +
				"  3\n" +
				"\n" +
				"  4\n" +
				"\n" +
				"  5\n" +
				"\n" +
				"  6\n" +
				"\n" +
				"  7\n" +
				"\n" +
				"  8\n", elements);
	}

	@Test
	public void shouldPrintEmptyRoot() {
		//given
		TreeNode<String> root = new TreeNode<>("root");

		// when
		String elements = TreeNode.printTree(root, " ");

		// then
		Assertions.assertEquals(" root\n", elements);
	}

	@Test
	public void shouldGetAllElements() {
		// given
		TreeNode<String> root = new TreeNode<>("1");
		TreeNode<String> node1 = root.addChild(new TreeNode<>("1"));
		TreeNode<String> node11 = node1.addChild(new TreeNode<>("11"));
		TreeNode<String> node2 = root.addChild(new TreeNode<>("2"));
		TreeNode<String> node3 = root.addChild(new TreeNode<>("3"));
		TreeNode<String> node4 = root.addChild(new TreeNode<>("4"));
		TreeNode<String> node5 = root.addChild(new TreeNode<>("5"));
		TreeNode<String> node6 = root.addChild(new TreeNode<>("6"));
		TreeNode<String> node7 = root.addChild(new TreeNode<>("7"));
		TreeNode<String> node8 = root.addChild(new TreeNode<>("8"));

		// when
		List<TreeNode<String>> allElements = TreeNode.getAllElements(root);

		// then
		Assertions.assertEquals(10, allElements.size());
	}

	@Test
	public void shouldGetAllOfEmptyRoot() {
		// given
		TreeNode<String> root = new TreeNode<>("1");

		// when
		List<TreeNode<String>> allElements = TreeNode.getAllElements(root);

		// then
		Assertions.assertEquals(1, allElements.size());
	}
}
