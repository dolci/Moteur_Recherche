package moteur_recherche;
import org.w3c.dom.*;

public class XPathUtils {

   private static int getNumberOfPrevSibling(Node node) {
      int nbPrevSibl = 0;
      Node prevNode = node;
      while ((prevNode = prevNode.getPreviousSibling()) != null) {
         if (prevNode.getNodeName().equals(node.getNodeName())) nbPrevSibl++;
      }
      return nbPrevSibl;
   }

   public static String getXPath(Node node) {
      if (node == null) return null;
      String path = "";
      int index = 0;
      switch (node.getNodeType()) {
         case Node.DOCUMENT_NODE:
            path = "/";
            break;
         case Node.ATTRIBUTE_NODE:
            path = getXPath(((Attr)node).getOwnerElement()) + "/@" + node.getNodeName();
            break;         
         case Node.ELEMENT_NODE:
            if (node.getParentNode().getNodeType()!= Node.DOCUMENT_NODE) {
               index = getNumberOfPrevSibling(node);
               path = getXPath(node.getParentNode()) + "/" + node.getNodeName() + "[" + Integer.toString(++index) + "]";
            } else {
               path = "/" + node.getNodeName();
            }
            break;
         case Node.CDATA_SECTION_NODE:
         case Node.TEXT_NODE:
            if (node.getParentNode().getNodeType() == Node.ATTRIBUTE_NODE) {
               path = getXPath(node.getParentNode());
            } else {
               index = getNumberOfPrevSibling(node);
               path = getXPath(node.getParentNode()) + "/text()[" + Integer.toString(++index) + "]";
            }
            break; 
         case Node.COMMENT_NODE:
            index = getNumberOfPrevSibling(node);
            path = getXPath(node.getParentNode()) + "/comment()[" + Integer.toString(++index) + "]";
            break;
      }
      return path;
   }
}