/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4448.81a70243a modeling language!*/


import java.util.*;

/**
 * Positioning
 */
// line 6 "model.ump"
// line 61 "model.ump"
public class Studie
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Studie Attributes
  private String name;
  private String beskrivelse;

  //Studie Associations
  private Institutt institutt;
  private List<Prosjekt> prosjekts;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Studie(String aName, String aBeskrivelse, Institutt aInstitutt)
  {
    name = aName;
    beskrivelse = aBeskrivelse;
    boolean didAddInstitutt = setInstitutt(aInstitutt);
    if (!didAddInstitutt)
    {
      throw new RuntimeException("Unable to create study due to institutt");
    }
    prosjekts = new ArrayList<Prosjekt>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
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

  public String getName()
  {
    return name;
  }

  public String getBeskrivelse()
  {
    return beskrivelse;
  }
  /* Code from template association_GetOne */
  public Institutt getInstitutt()
  {
    return institutt;
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
  public boolean setInstitutt(Institutt aInstitutt)
  {
    boolean wasSet = false;
    if (aInstitutt == null)
    {
      return wasSet;
    }

    Institutt existingInstitutt = institutt;
    institutt = aInstitutt;
    if (existingInstitutt != null && !existingInstitutt.equals(aInstitutt))
    {
      existingInstitutt.removeStudy(this);
    }
    institutt.addStudy(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfProsjekts()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Prosjekt addProsjekt(String aNavn, String aBeskrivelse, Bedrift aBedrift, Bruker aEier)
  {
    return new Prosjekt(aNavn, aBeskrivelse, this, aBedrift, aEier);
  }

  public boolean addProsjekt(Prosjekt aProsjekt)
  {
    boolean wasAdded = false;
    if (prosjekts.contains(aProsjekt)) { return false; }
    Studie existingStudie = aProsjekt.getStudie();
    boolean isNewStudie = existingStudie != null && !this.equals(existingStudie);
    if (isNewStudie)
    {
      aProsjekt.setStudie(this);
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
    //Unable to remove aProsjekt, as it must always have a studie
    if (!this.equals(aProsjekt.getStudie()))
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
    Institutt placeholderInstitutt = institutt;
    this.institutt = null;
    if(placeholderInstitutt != null)
    {
      placeholderInstitutt.removeStudy(this);
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
            "name" + ":" + getName()+ "," +
            "beskrivelse" + ":" + getBeskrivelse()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "institutt = "+(getInstitutt()!=null?Integer.toHexString(System.identityHashCode(getInstitutt())):"null");
  }
}