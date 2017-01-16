package net.veerkesto

import org.junit.Test
import org.junit.Assert
class PrefixTreeTest {
    @Test
    fun oneChar() {
        val sortedWordsWithPayload = arrayOf(Pair("A",1))
        val prefixTree = PrefixTree(sortedWordsWithPayload)
        Assert.assertEquals(NodePointer(0, true, 1), prefixTree.lookup(0, 0, 'A'))
        Assert.assertNull(prefixTree.lookup(0, 0, 'B'))
    }

    @Test
    fun oneWord() {
        val sortedWordsWithPayload = arrayOf(Pair("AB",1))
        val prefixTree = PrefixTree(sortedWordsWithPayload)
        Assert.assertEquals(NodePointer(0, false, null), prefixTree.lookup(0, 0, 'A'))
        Assert.assertEquals(NodePointer(0, true, 1), prefixTree.lookup(0, 1, 'B'))
    }

    @Test
    fun twoWords() {
        val sortedWordsWithPayload = arrayOf(Pair("A",1), Pair("AB",2))
        val prefixTree = PrefixTree(sortedWordsWithPayload)
        Assert.assertEquals(NodePointer(0, true, 1), prefixTree.lookup(0, 0, 'A'))
        Assert.assertEquals(NodePointer(1, true, 2), prefixTree.lookup(0, 1, 'B'))
    }

    @Test
    fun threeWords() {
        val sortedWordsWithPayload = arrayOf(Pair("A",1), Pair("AB",2), Pair("CXX",3))
        val prefixTree = PrefixTree(sortedWordsWithPayload)
        Assert.assertEquals(NodePointer(0, true, 1), prefixTree.lookup(0, 0, 'A'))
        Assert.assertEquals(NodePointer(1, true, 2), prefixTree.lookup(0, 1, 'B'))
        Assert.assertEquals(NodePointer(2, false, null), prefixTree.lookup(0, 0, 'C'))
        Assert.assertEquals(NodePointer(2, false, null), prefixTree.lookup(2, 1, 'X'))
        Assert.assertEquals(NodePointer(2, true, 3), prefixTree.lookup(2, 2, 'X'))
    }
}


