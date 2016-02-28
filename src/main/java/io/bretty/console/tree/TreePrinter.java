package io.bretty.console.tree;

import java.util.List;

/**
 * A static utility class that converts a tree into a formatted string
 */
public class TreePrinter {

    private TreePrinter() {
    }

    private static void process(PrintableTreeNode current, String prefix, boolean isRoot, boolean lastInPeers, StringBuilder stringBuilder){
        stringBuilder.append(prefix);
        if(!isRoot){
            stringBuilder.append((lastInPeers ? "└── " : "├── "));
        }
        else{
            stringBuilder.append(" ── ");
        }
        stringBuilder.append(current.name());
        stringBuilder.append('\n');

        List<? extends PrintableTreeNode> children = current.children();

        String indentation = isRoot? "    " : "    ";
        for(int i = 0; i < children.size() - 1; ++i){
            process(children.get(i), prefix + (lastInPeers ? indentation : "│   "), false, false, stringBuilder);
        }

        if(children.size() > 0){
            process(children.get(children.size() - 1), prefix + (lastInPeers ? indentation : "│   "), false, true, stringBuilder);
        }
    }

    /**
     *
     * @param root - the root node of the tree
     * @return a formatted string that is ready to be printed
     */
    public static String toString(PrintableTreeNode root){
        StringBuilder stringBuilder = new StringBuilder();
        process(root, "", true, true, stringBuilder);
        return stringBuilder.toString();
    }
}
