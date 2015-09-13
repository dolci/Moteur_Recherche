package moteur_recherche;
public class StopList {
    


static boolean motVide(String s){
    if (
             s.equals("auquel") || s.equals("au") ||
                   s.equals("assez") || s.equals("as") || s.equals("alors") ||
                   s.equals("ait") || s.equals("ais") || s.equals("ainsi") ||
                   s.equals("aient") || s.equals("aient") || s.equals("aie") ||
                   s.equals("ai") || s.equals("afin") ||
                   s.equals("à") || s.equals("a") ||
    
              s.equals("certaine") || s.equals("certain") ||
                   s.equals("celui") || s.equals("celles") || s.equals("celle") ||
                   s.equals("cela") || s.equals("ce") || s.equals("car") ||
                   s.equals("ça") || s.equals("ca") || s.equals("beaucoup") ||
                   s.equals("avoir") || s.equals("avec") ||
                   s.equals("avant") || s.equals("avait") || s.equals("avais") ||
                   s.equals("avaient") || s.equals("auxquels") ||
                   s.equals("auxquelles") || s.equals("aux") || s.equals("aussi") ||
                   s.equals("auquelle")||   s.equals("de") || s.equals("dans") ||
                   s.equals("d") || s.equals("contre") || s.equals("conseil") ||
                   s.equals("connaît") || s.equals("connait") || s.equals("concernant") ||
                   s.equals("concern") || s.equals("comment") || s.equals("comme") ||
                   s.equals("ci") || s.equals("chez") ||
                   s.equals("chaque") || s.equals("chacune") || s.equals("chacun") ||
                   s.equals("ceux") || s.equals("cette") ||
                   s.equals("cet") || s.equals("ces") || s.equals("certains") ||
                   s.equals("certaines")||  s.equals("e") || s.equals("dus") ||
                   s.equals("duquel") || s.equals("dû") || s.equals("du") ||
                   s.equals("dont") || s.equals("donc") || s.equals("doivent") ||
                   s.equals("doit") || s.equals("dois") || s.equals("différents") ||
                   s.equals("differents") || s.equals("différentes") ||
                   s.equals("differentes") || s.equals("différente") || s.equals("differente") ||
                   s.equals("différent") || s.equals("different") ||
                   s.equals("differe") || s.equals("desquels") || s.equals("desquelles") ||
                   s.equals("des")||  s.equals("etiez") || s.equals("été") ||
                   s.equals("ete") || s.equals("etc") || s.equals("étant") ||
                   s.equals("etant") || s.equals("était") || s.equals("etait") ||
                   s.equals("étais") || s.equals("etais") || s.equals("étaient") ||
                   s.equals("etaient") || s.equals("etai") ||
                   s.equals("et") || s.equals("est") || s.equals("es") ||
                   s.equals("entre") || s.equals("ensuite") ||
                   s.equals("encore") || s.equals("en") || s.equals("elles") ||
                   s.equals("elle")||  s.equals("feras") || s.equals("fera") ||
                   s.equals("faits") || s.equals("faites") || s.equals("faite") ||
                   s.equals("fait") || s.equals("fais") || s.equals("faire") ||
                   s.equals("fai") || s.equals("explique") || s.equals("expliqu") ||
                   s.equals("évidence") || s.equals("evidence") ||
                   s.equals("evidenc") || s.equals("eux") || s.equals("eu") ||
                   s.equals("être") || s.equals("etre") ||
                   s.equals("étions") || s.equals("etions") || s.equals("etion") ||
                   s.equals("étiez")||  s.equals("laquelle") || s.equals("laquell") ||
                   s.equals("la") || s.equals("l") || s.equals("je") ||
                   s.equals("jamais") || s.equals("j") || s.equals("intéressé") ||
                   s.equals("interesse") || s.equals("intéressant") || s.equals("interessant") ||
                   s.equals("intere") || s.equals("ici") ||
                   s.equals("ils") || s.equals("grâce") || s.equals("grace") ||
                   s.equals("font") || s.equals("finit") ||
                   s.equals("finis") || s.equals("finies") || s.equals("finie") ||
                   s.equals("fini")||    s.equals("mes") || s.equals("mêmes") ||
                   s.equals("memes") || s.equals("même") || s.equals("meme") ||
                   s.equals("me") || s.equals("mal") || s.equals("mais") ||
                   s.equals("maintenant") || s.equals("mainten") || s.equals("ma") ||
                   s.equals("m") || s.equals("lui") ||
                   s.equals("lorsque") || s.equals("lors") || s.equals("leurs") ||
                   s.equals("leur") || s.equals("lesquels") ||
                   s.equals("lesquelles") || s.equals("les") || s.equals("lequel") ||
                   s.equals("le")||s.equals("ont") || s.equals("on") ||
                   s.equals("obtenus") || s.equals("obtenues") || s.equals("obtenue") ||
                   s.equals("obtenu") || s.equals("nous") || s.equals("nou") ||
                   s.equals("nôtres") || s.equals("notres") || s.equals("nôtre") ||
                   s.equals("notre") || s.equals("nos") ||
                   s.equals("non") || s.equals("no") || s.equals("ni") ||
                   s.equals("ne") || s.equals("n") ||
                   s.equals("mon") || s.equals("moins") || s.equals("moi") ||
                   s.equals("mettre")||  s.equals("pres") || s.equals("prendre") ||
                   s.equals("pouvons") || s.equals("pouvez") || s.equals("pourquoi") ||
                   s.equals("pourquo") || s.equals("pour") || s.equals("plus") ||
                   s.equals("peux") || s.equals("peuvent") || s.equals("peut") ||
                   s.equals("peu") || s.equals("permet") ||
                   s.equals("pas") || s.equals("part") || s.equals("pars") ||
                   s.equals("parle") || s.equals("parfois") ||
                   s.equals("par") || s.equals("où") || s.equals("ou") ||
                   s.equals("or")||    s.equals("si") || s.equals("seuls") ||
                   s.equals("seul") || s.equals("ses") || s.equals("seront") ||
                   s.equals("se") || s.equals("savoir") || s.equals("sa") ||
                   s.equals("quoi") || s.equals("qui") || s.equals("quels") ||
                   s.equals("quelques") || s.equals("quelles") ||
                   s.equals("quelle") || s.equals("quel") || s.equals("que") ||
                   s.equals("quand") || s.equals("qu") ||
                   s.equals("principaux") || s.equals("principal") || s.equals("princip") ||
                   s.equals("près")  ||  s.equals("y") || s.equals("vous") || s.equals("voulu") ||
                   s.equals("voulez") || s.equals("vont") ||
                   s.equals("veux") || s.equals("veut") || s.equals("vers") ||
                   s.equals("venir") || s.equals("va") ||
                   s.equals("utilises") || s.equals("uilisés") || s.equals("utilisees") ||
                   s.equals("utilisées") || s.equals("utilisée") ||
                   s.equals("utilisee") || s.equals("utilisé") || s.equals("uns") ||
                   s.equals("utilise") || s.equals("such") ||
                   s.equals("unes") || s.equals("une") || s.equals("un") ||
                   s.equals("tu") || s.equals("trop") ||
                   s.equals("très") || s.equals("tres") || s.equals("traite") ||
                   s.equals("toutes") || s.equals("toute") ||
                   s.equals("tout") || s.equals("tous") || s.equals("toujours") ||
                   s.equals("toujour") || s.equals("ton") ||
                   s.equals("tes") || s.equals("tels") || s.equals("telles") ||
                   s.equals("tellement") || s.equals("telleme") ||
                   s.equals("telle") || s.equals("tel") || s.equals("te") ||
                   s.equals("ta") || s.equals("t") || s.equals("sur") ||
                   s.equals("suis") || s.equals("sui") || s.equals("souvent") ||
                   s.equals("sous") || s.equals("sont") ||
                   s.equals("son") || s.equals("soit") || s.equals("soient")) 
                   
        return true;
    else 
        return false;
}//fin motVide


}
