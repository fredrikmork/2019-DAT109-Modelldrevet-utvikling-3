/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4448.81a70243a modeling language!*/


import java.util.*;

// line 54 "model.ump"
// line 114 "model.ump"
public class Bruker
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Bruker Attributes
  private String brukernavn;
  private String passord;
  private bool utgatt;

  //Bruker Associations
  private Brukergruppe gruppe;
  private List<Prosjekt> prosjekts;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Bruker(String aBrukernavn, String aPassord, bool aUtgatt, Brukergruppe aGruppe)
  {
    brukernavn = aBrukernavn;
    passord = aPassord;
    utgatt = aUtgatt;
    boolean didAddGruppe = setGruppe(aGruppe);
    if (!didAddGruppe)
    {
      throw new RuntimeException("Unable to create bruker due to gruppe");
    }
    prosjekts = new ArrayList<Prosjekt>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setBrukernavn(String aBrukernavn)
  {
    boolean wasSet = false;
    brukernavn = aBrukernavn;
    wasSet = true;
    return wasSet;
  }

  public boolean setPassord(String aPassord)
  {
    boolean wasSet = false;
    passord = aPassord;
    wasSet = true;
    return wasSet;
  }

  public boolean setUtgatt(bool aUtgatt)
  {
    boolean wasSet = false;
    utgatt = aUtgatt;
    wasSet = true;
    return wasSet;
  }

  public String getBrukernavn()
  {
    return brukernavn;
  }

  public String getPassord()
  {
    return passord;
  }

  public bool getUtgatt()
  {
    return utgatt;
  }
  /* Code from template association_GetOne */
  public Brukergruppe getGruppe()
  {
    return gruppe;
  }
  /* Code from template association_GetMany */
  public Prosjekt getProsjekt(int index)
  {
    Prosjekt aProsjekt = prosjekts.get(index);
    return aProsjekt;
  }

  public List<Prosjekt> getProsjekts()
  {
    List<Prosjekt> newProsjekts = Collections.unmodifiableList(prosjekts);
    return newProsjekts;
  }

  public int numberOfProsjekts()
  {
    int number = prosjekts.size();
    return number;
  }

  public boolean hasProsjekts()
  {
    boolean has = prosjekts.size() > 0;
    return has;
  }

  public int indexOfProsjekt(Prosjekt aProsjekt)
  {
    int index = prosjekts.indexOf(aProsjekt);
    return index;
  }
  /* Code from template association_SetOneToMany */
  public boolean setGruppe(Brukergruppe aGruppe)
  {
    boolean wasSet = false;
    if (aGruppe == null)
    {
      return wasSet;
    }

    Brukergruppe existingGruppe = gruppe;
    gruppe = aGruppe;
    if (existingGruppe != null && !existingGruppe.equals(aGruppe))
    {
      existingGruppe.removeBruker(this);
    }
    gruppe.addBruker(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfProsjekts()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Prosjekt addProsjekt(String aNavn, String aBeskrivelse, Studie aStudie, Bedrift aBedrift)
  {
    return new Prosjekt(aNavn, aBeskrivelse, aStudie, aBedrift, this);
  }

  public boolean addProsjekt(Prosjekt aProsjekt)
  {
    boolean wasAdded = false;
    if (prosjekts.contains(aProsjekt)) { return false; }
    Bruker existingEier = aProsjekt.getEier();
    boolean isNewEier = existingEier != null && !this.equals(existingEier);
    if (isNewEier)
    {
      aProsjekt.setEier(this);
    }
    else
    {
      prosjekts.add(aProsjekt);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeProsjekt(Prosjekt aProsjekt)
  {
    boolean wasRemoved = false;
    //Unable to remove aProsjekt, as it must always have a eier
    if (!this.equals(aProsjekt.getEier()))
    {
      prosjekts.remove(aProsjekt);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addProsjektAt(Prosjekt aProsjekt, int index)
  {  
    boolean wasAdded = false;
    if(addProsjekt(aProsjekt))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfProsjekts()) { index = numberOfProsjekts() - 1; }
      prosjekts.remove(aProsjekt);
      prosjekts.add(index, aProsjekt);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveProsjektAt(Prosjekt aProsjekt, int index)
  {
    boolean wasAdded = false;
    if(prosjekts.contains(aProsjekt))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfProsjekts()) { index = numberOfProsjekts() - 1; }
      prosjekts.remove(aProsjekt);
      prosjekts.add(index, aProsjekt);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addProsjektAt(aProsjekt, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    Brukergruppe placeholderGruppe = gruppe;
    this.gruppe = null;
    if(placeholderGruppe != null)
    {
      placeholderGruppe.removeBruker(this);
    }
    for(int i=prosjekts.size(); i > 0; i--)
    {
      Prosjekt aProsjekt = prosjekts.get(i - 1);
      aProsjekt.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "brukernavn" + ":" + getBrukernavn()+ "," +
            "passord" + ":" + getPassord()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "utgatt" + "=" + (getUtgatt() != null ? !getUtgatt().equals(this)  ? getUtgatt().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "gruppe = "+(getGruppe()!=null?Integer.toHexString(System.identityHashCode(getGruppe())):"null");
  }
}