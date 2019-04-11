/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4448.81a70243a modeling language!*/


import java.sql.Date;

// line 37 "model.ump"
// line 93 "model.ump"
public class Stemme
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Stemme Attributes
  private String epost;
  private int verdi;
  private Date tidspunkt;

  //Stemme Associations
  private Arrangementdeltagelse arrangementdeltagelse;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Stemme(String aEpost, int aVerdi, Date aTidspunkt, Arrangementdeltagelse aArrangementdeltagelse)
  {
    epost = aEpost;
    verdi = aVerdi;
    tidspunkt = aTidspunkt;
    boolean didAddArrangementdeltagelse = setArrangementdeltagelse(aArrangementdeltagelse);
    if (!didAddArrangementdeltagelse)
    {
      throw new RuntimeException("Unable to create stemme due to arrangementdeltagelse");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setEpost(String aEpost)
  {
    boolean wasSet = false;
    epost = aEpost;
    wasSet = true;
    return wasSet;
  }

  public boolean setVerdi(int aVerdi)
  {
    boolean wasSet = false;
    verdi = aVerdi;
    wasSet = true;
    return wasSet;
  }

  public boolean setTidspunkt(Date aTidspunkt)
  {
    boolean wasSet = false;
    tidspunkt = aTidspunkt;
    wasSet = true;
    return wasSet;
  }

  public String getEpost()
  {
    return epost;
  }

  public int getVerdi()
  {
    return verdi;
  }

  public Date getTidspunkt()
  {
    return tidspunkt;
  }
  /* Code from template association_GetOne */
  public Arrangementdeltagelse getArrangementdeltagelse()
  {
    return arrangementdeltagelse;
  }
  /* Code from template association_SetOneToMany */
  public boolean setArrangementdeltagelse(Arrangementdeltagelse aArrangementdeltagelse)
  {
    boolean wasSet = false;
    if (aArrangementdeltagelse == null)
    {
      return wasSet;
    }

    Arrangementdeltagelse existingArrangementdeltagelse = arrangementdeltagelse;
    arrangementdeltagelse = aArrangementdeltagelse;
    if (existingArrangementdeltagelse != null && !existingArrangementdeltagelse.equals(aArrangementdeltagelse))
    {
      existingArrangementdeltagelse.removeStemme(this);
    }
    arrangementdeltagelse.addStemme(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Arrangementdeltagelse placeholderArrangementdeltagelse = arrangementdeltagelse;
    this.arrangementdeltagelse = null;
    if(placeholderArrangementdeltagelse != null)
    {
      placeholderArrangementdeltagelse.removeStemme(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "epost" + ":" + getEpost()+ "," +
            "verdi" + ":" + getVerdi()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "tidspunkt" + "=" + (getTidspunkt() != null ? !getTidspunkt().equals(this)  ? getTidspunkt().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "arrangementdeltagelse = "+(getArrangementdeltagelse()!=null?Integer.toHexString(System.identityHashCode(getArrangementdeltagelse())):"null");
  }
}