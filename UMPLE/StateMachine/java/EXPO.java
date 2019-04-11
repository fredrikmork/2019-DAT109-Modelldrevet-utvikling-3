/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4448.81a70243a modeling language!*/



/**
 * @@@skipcppcompile - Contains Java Code
 * @@@skipphpcompile - Contains Java Code
 * @@@skiprubycompile - Contains Java Code
 */
// line 2 "model.ump"
// line 63 "model.ump"
// line 68 "model.ump"
public class EXPO
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //EXPO State Machines
  public enum Bruker { LoggetInn, LoggedUt }
  private Bruker bruker;
  public enum Admin { adminSide, visStatistikk, visIndex }
  private Admin admin;
  public enum Stemming { KanIkkeStemme, KanStemme }
  private Stemming stemming;
  public enum Stemme { stemmePaaProsjekt, TakkForStemme }
  private Stemme stemme;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public EXPO()
  {
    setBruker(Bruker.LoggetInn);
    setAdmin(Admin.adminSide);
    setStemming(Stemming.KanIkkeStemme);
    setStemme(Stemme.stemmePaaProsjekt);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public String getBrukerFullName()
  {
    String answer = bruker.toString();
    return answer;
  }

  public String getAdminFullName()
  {
    String answer = admin.toString();
    return answer;
  }

  public String getStemmingFullName()
  {
    String answer = stemming.toString();
    return answer;
  }

  public String getStemmeFullName()
  {
    String answer = stemme.toString();
    return answer;
  }

  public Bruker getBruker()
  {
    return bruker;
  }

  public Admin getAdmin()
  {
    return admin;
  }

  public Stemming getStemming()
  {
    return stemming;
  }

  public Stemme getStemme()
  {
    return stemme;
  }

  public boolean loggerUt()
  {
    boolean wasEventProcessed = false;
    
    Bruker aBruker = bruker;
    switch (aBruker)
    {
      case LoggetInn:
        // line 5 "model.ump"
        loggUt();
        setBruker(Bruker.LoggedUt);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean loggeInn()
  {
    boolean wasEventProcessed = false;
    
    Bruker aBruker = bruker;
    switch (aBruker)
    {
      case LoggedUt:
        // line 8 "model.ump"
        loggInn();
        setBruker(Bruker.LoggetInn);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean erAdmin()
  {
    boolean wasEventProcessed = false;
    
    Admin aAdmin = admin;
    switch (aAdmin)
    {
      case adminSide:
        setAdmin(Admin.visStatistikk);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean erIkkeAdmin()
  {
    boolean wasEventProcessed = false;
    
    Admin aAdmin = admin;
    switch (aAdmin)
    {
      case adminSide:
        setAdmin(Admin.visIndex);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean skriverEpost()
  {
    boolean wasEventProcessed = false;
    
    Stemming aStemming = stemming;
    switch (aStemming)
    {
      case KanIkkeStemme:
        // line 27 "model.ump"
        lagreEpost();
        setStemming(Stemming.KanStemme);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean fjernerCookies()
  {
    boolean wasEventProcessed = false;
    
    Stemming aStemming = stemming;
    switch (aStemming)
    {
      case KanStemme:
        // line 30 "model.ump"
        fjerneEpost();
        setStemming(Stemming.KanIkkeStemme);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean harStemtPaaProsjektFor()
  {
    boolean wasEventProcessed = false;
    
    Stemme aStemme = stemme;
    switch (aStemme)
    {
      case stemmePaaProsjekt:
        // line 36 "model.ump"
        slettGammelStemme();stem();
        setStemme(Stemme.TakkForStemme);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean harIkkeStemtPaaProsjektFor()
  {
    boolean wasEventProcessed = false;
    
    Stemme aStemme = stemme;
    switch (aStemme)
    {
      case stemmePaaProsjekt:
        // line 37 "model.ump"
        stem();
        setStemme(Stemme.TakkForStemme);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void setBruker(Bruker aBruker)
  {
    bruker = aBruker;
  }

  private void setAdmin(Admin aAdmin)
  {
    admin = aAdmin;
  }

  private void setStemming(Stemming aStemming)
  {
    stemming = aStemming;
  }

  private void setStemme(Stemme aStemme)
  {
    stemme = aStemme;
  }

  public void delete()
  {}

  // line 48 "model.ump"
  public void loggUt(){
    
  }

  // line 51 "model.ump"
  public void loggInn(){
    
  }

  // line 53 "model.ump"
  public void lagreEpost(){
    
  }

  // line 55 "model.ump"
  public void fjerneEpost(){
    
  }

}