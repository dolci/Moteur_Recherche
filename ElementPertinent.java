/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moteur_recherche;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Med
 */
public class ElementPertinent extends ElementCol implements Comparable {
    double tf=0;
    String chemin_doc;
    int ref_doc;
    double idf=0;
    double poid=0;
    public ElementPertinent(int r,String c,int b, String d,int ref_doc)
    {
        super(r,c,b);
       chemin_doc=d;
        this.ref_doc=ref_doc;
    }  
    public void setScore(double s)
    {
        poid=s;
    }
    public void setIdf(double s)
    {
        idf=s;
    }
    public double getIdf()
    {
        return idf;
    }
      public void setTf(double s)
    {
        tf=s;
    }
    public double getTf()
    {
        return tf;
    }
    public void setRefDoc(int n)
    {
      ref_doc=n;
    }
    public int getRefDoc()
    {
     return ref_doc;
    }
    public double getScore()
    {
        return poid;
    }
    public String getCheminDoc()
    {
        return chemin_doc;
    }
    public int compareTo(Object ob) { 
        if(((ElementPertinent)ob).getScore()-getScore()>0)
            return 1;
        else if (((ElementPertinent)ob).getScore()-getScore()<0)
                return -1;
        else
                    
        return 0;
    }
    public  boolean equals(Object d)
    {
        return this.getRefElem()==((ElementPertinent)d).getRefElem();
    }
}
