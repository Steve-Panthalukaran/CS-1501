/**
 * Pre-test for CS1501
 *
 * This presents a brief self-check on Java programming skills I am expecting
 * students to possess coming out of CS0445.
 *
 * @author    Dr. Farnan
 */
import java.util.NoSuchElementException;

/*
 * Write a definition of `MyHashMap<K, V>` that implements `MyHashMap_Inter<K,
 * V>` (defined below) using entirely your own code (e.g., do not import any
 * Java classes except for `java.util.NoSuchElementException`). You should use
 * separate chaining for collision resolution (with your own linked-list!). You
 * can use the `.hashCode()` method of any key type to get an integer
 * representation of a key.
 *
 * Note that your implementation should implement Comparable against other
 * MyHashMaps, and they sould be compared on the number of key/value pairs
 * they contain (i.e., the result of their `.len()` methods).
 */

// YOUR CODE HERE

interface MyHashMap_Inter<K, V> extends Comparable<MyHashMap_Inter<K, V>> {
    /**
     * Add a new key to the MyHashMap
     *
     * @param     key Generic type key to be added to the MyHashMap
     * @param    value Generic type value to be added to the MyHashMap for the
     *            specified key
     */
    public void insert(K key, V value);

    /**
     * Check if the MyHashMap contains a key
     *
     * @param    key Generic type key to look for in the MyHashMap
     *
     * @return    true if key is in the MyHashMap, false otherwise
     */
    public boolean contains(K key);

    /**
     * Fetch the value associated with a key in the MyHashMap contains a key
     *
     * @param    key Generic type key to look for in the MyHashMap
     *
     * @return    The value associated with that key
     *
     * @throws    NoSuchElementException If the key is not contained within the
     *             MyHashMap
     */
    public V get(K key);

    /**
     * Return the number of key/value pairs in the MyHashMap
     *
     * @return    The number of key/value pairs in the MyHashMap
     */
    public int len();
}

public class PreTest {
    // Tests to check that your MyHashMap implementation works correctly
    public static void main(String args[]) {
        System.out.println("Starting MyHashMap tests..."); int i = 0;

        MyHashMap<Integer, String> hm = new MyHashMap<Integer, String>();
        hm.insert(7, "seven");
        hm.insert(10, "ten");
        hm.insert(5, "five");

        checker(++i, hm.contains(7), "hm does not contain 7");
        checker(++i, !hm.contains(50), "hm should not contain 50");
        checker(++i, hm.get(5).equals("five"), "hm did not correctly fetch 5");

        MyHashMap<Integer, String> hm2 = new MyHashMap<Integer, String>();
        hm2.insert(2, "two");
        hm2.insert(15, "fifteen");
        hm2.insert(8, "eight");

        checker(++i, hm.compareTo(hm2) == 0, "hm and hm2 should compare as equal in size");

        MyHashMap<String, Integer> big_hm = new MyHashMap<String, Integer>();
        for (int j = 0; j < 1000; j++) {
            big_hm.insert(Integer.toString(j), j);
        }

        checker(++i, big_hm.contains("99"), "big_hm does not contain 99");
        checker(++i, !big_hm.contains("1000"), "big_hm should not contian 100");
        checker(++i, big_hm.get("15") == 15, "big_hm did not correctly fetch \"15\"");
        boolean found = true;
        try {
            big_hm.get("1001");
        }
        catch (NoSuchElementException e) {
            found = false;
        }
        checker(++i, !found, "big_hm should not fetch \"101\"");

        MyHashMap<String, Integer> small_hm = new MyHashMap<String, Integer>();
        for (int j = 0; j < 10; j++) {
            small_hm.insert(Integer.toString(j), j);
        }

        checker(++i, big_hm.compareTo(small_hm) > 0, "big_hm should be bigger than small_hm");
        checker(++i, small_hm.compareTo(big_hm) < 0, "small_hm should be smaller than big_hm");
    }

    public static void checker(int i, boolean result, String error_message) {
        if (result) {
            System.out.println("\tPassed test " + Integer.toString(i));
        }
        else {
            System.out.println("\tFAILED test " + Integer.toString(i) + ":\n\t\t" + error_message);
        }
    }
}