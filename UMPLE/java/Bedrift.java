/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4448.81a70243a modeling language!*/


import java.util.*;

// line 12 "model.ump"
// line 104 "model.ump"
public class Bedrift
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Bedrift Attributes
  private String navn;
  private String beskrivelse;

  //Bedrift Associations
  private List<Prosjekt> prosjekts;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Bedrift(String aNavn, String aBeskrivelse)
  {
    navn = aNavn;
    beskrivelse = aBeskrivelse;
    prosjekts = new ArrayList<Prosjekt>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setNavn(String aNavn)
  {
    boolean wasSet = false;
    navn = aNavn;
    wasSet = true;
    return wasSet;
  }

  public boolean setBeskrivelse(String aBeskrivelse)
  {
    boolean wasSet = false;
    beskrivelse = aBeskrivelse;
    wasSet = true;
    return wasSet;
  }

  public String getNavn()
  {
    return navn;
  }

  public String getBeskrivelse()
  {
    return beskrivelse;
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfProsjekts()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Prosjekt addProsjekt(String aNavn, String aBeskrivelse, Studie aStudie, Bruker aEier)
  {
    return new Prosjekt(aNavn, aBeskrivelse, aStudie, this, aEier);
  }

  public boolean addProsjekt(Prosjekt aProsjekt)
  {
    boolean wasAdded = false;
    if (prosjekts.contains(aProsjekt)) { return false; }
    Bedrift existingBedrift = aProsjekt.getBedrift();
    boolean isNewBedrift = existingBedrift != null && !this.equals(existingBedrift);
    if (isNewBedrift)
    {
      aProsjekt.setBedrift(this);
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
    //Unable to remove aProsjekt, as it must always have a bedrift
    if (!this.equals(aProsjekt.getBedrift()))
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
    for(int i=prosjekts.size(); i > 0; i--)
    {
      Prosjekt aProsjekt = prosjekts.get(i - 1);
      aProsjekt.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "navn" + ":" + getNavn()+ "," +
            "beskrivelse" + ":" + getBeskrivelse()+ "]";
  }
}