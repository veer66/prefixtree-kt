package net.veerkesto

import java.util.*

data class NodePointer<P>(val childId: Int, val isFinal: Boolean, val payload: P?)
data class NodeKey(val nodeId: Int, val offset:Int, val ch: Char)

class PrefixTree<P>(sortedWordsWithPayload: Array<Pair<String, P>>) {
    var tab = HashMap<NodeKey, NodePointer<P>>()
    init {
        for ((i, wordWithPayload) in sortedWordsWithPayload.withIndex()) {
            val (word, payload) = wordWithPayload
            var rowNo = 0
            for ((j, ch) in word.withIndex()) {
                val isFinal = ((j + 1) == word.length)
                val key = NodeKey(rowNo, j, ch)
                val child = tab[key]
                if (child == null) {
                    val pointer = NodePointer<P>(i, isFinal, if (isFinal) { payload } else { null })
                    tab[key] = pointer
                    rowNo = i
                } else {
                    rowNo = child.childId
                }
            }
        }
    }
    fun  lookup(nodeId: Int, offset: Int, ch: Char): NodePointer<P>? {
      return tab[NodeKey(nodeId, offset, ch)]
   }

}

