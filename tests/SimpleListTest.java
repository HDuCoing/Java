package tutorial5.src.nz.ac.massey.cs.pp.tutorial5.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import tutorial5.src.nz.ac.massey.cs.pp.tutorial5.SimpleList;

class SimpleListTest {

	SimpleList<String> list;
	
	@BeforeEach
	void setup() {
		list = new SimpleList<>();
	}
	
	@Test
	void testSize() {
		assertTrue(list.size() == 0);
	}
	
	@Test
	void testAddSize() {
		list.add("testString");
		assertTrue(list.size() == 1);
	}
	
	@Test
	void testAddLots() {
		int itemsToAdd = 10_000;
		for (int i = 0; i < itemsToAdd; i++) {
			list.add(Integer.toString(i));
		}
		assertTrue(list.size() == itemsToAdd);
	}
	
	@Test
	void testGet() {
		list.add("testString");
		assertEquals(list.get(0), "testString");
	}
	
	@Test
	void testGetLots() {
		int itemsToAdd = 10_000;
		for (int i = 0; i < itemsToAdd; i++) {
			list.add(Integer.toString(i));
		}
		assertEquals(list.get(itemsToAdd - 1), Integer.toString(itemsToAdd - 1));
	}
	
	@Test
	void testGetNegative() {
		assertNull(list.get(-1));
	}
	
	@Test
	void testGetTooMany() {
		assertNull(list.get(0));
	}
	
	@Test
	void testRemove() {
		list.add("test");
		assertTrue(list.size() == 1);
		list.remove(0);
		assertTrue(list.size() == 0);
	}
	
	@Test
	void testRemoveNegative() {
		int initialSize = list.size();
		list.remove(-1);
		assertEquals(list.size(), initialSize);
	}
	
	@Test
	void testRemoveLargerThanSize() {
		assertNull(list.get(0));
	}
	
	@Test
	void testNullNotReturnedAfterRemove() {
		SimpleList<Integer> list2 = new SimpleList<>();
		for (int i = 0; i < 5; i++) {
			list2.add(i);
		}
		list2.remove(0);
		assertTrue(list2.get(0) == 1);
		assertNull(list2.get(4));
	}
	
	
	@Test
	void testGenericsWork() {
		SimpleList<Integer> list2 = new SimpleList<>();
		list2.add(10);
		assertTrue(list2.get(0) == 10);
		assertTrue(list2.get(0) instanceof Integer);
	}
	
	@AfterEach
	void tearDown() {
		list = null;
	}

}
