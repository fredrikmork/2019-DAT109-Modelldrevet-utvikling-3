/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4448.81a70243a modeling language!*/


import java.util.*;

// line 17 "model.ump"
// line 67 "model.ump"
public class Prosjekt
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Prosjekt Attributes
  private String navn;
  private String beskrivelse;

  //Prosjekt Associations
  private Studie studie;
  private Bedrift bedrift;
  private Bruker eier;
  private List<Arrangementdeltagelse> arrangementdeltagelses;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Prosjekt(String aNavn, String aBeskrivelse, Studie aStudie, Bedrift aBedrift, Bruker aEier)
  {
    navn = aNavn;
    beskrivelse = aBeskrivelse;
    boolean didAddStudie = setStudie(aStudie);
    if (!didAddStudie)
    {
      throw new RuntimeException("Unable to create prosjekt due to studie");
    }
    boolean didAddBedrift = setBedrift(aBedrift);
    if (!didAddBedrift)
    {
      throw new RuntimeException("Unable to create prosjekt due to bedrift");
    }
    boolean didAddEier = setEier(aEier);
    if (!didAddEier)
    {
      throw new RuntimeException("Unable to create prosjekt due to eier");
    }
    arrangementdeltagelses = new ArrayList<Arrangementdeltagelse>();
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
  /* Code from template association_GetOne */
  public Studie getStudie()
  {
    return studie;
  }
  /* Code from template association_GetOne */
  public Bedrift getBedrift()
  {
    return bedrift;
  }
  /* Code from template association_GetOne */
  public Bruker getEier()
  {
    return eier;
  }
  /* Code from template association_GetMany */
  public Arrangementdeltagelse getArrangementdeltagelse(int index)
  {
    Arrangementdeltagelse aArrangementdeltagelse = arrangementdeltagelses.get(index);
    return aArrangementdeltagelse;
  }

  public List<Arrangementdeltagelse> getArrangementdeltagelses()
  {
    List<Arrangementdeltagelse> newArrangementdeltagelses = Collections.unmodifiableList(arrangementdeltagelses);
    return newArrangementdeltagelses;
  }

  public int numberOfArrangementdeltagelses()
  {
    int number = arrangementdeltagelses.size();
    return number;
  }

  public boolean hasArrangementdeltagelses()
  {
    boolean has = arrangementdeltagelses.size() > 0;
    return has;
  }

  public int indexOfArrangementdeltagelse(Arrangementdeltagelse aArrangementdeltagelse)
  {
    int index = arrangementdeltagelses.indexOf(aArrangementdeltagelse);
    return index;
  }
  /* Code from template association_SetOneToMany */
  public boolean setStudie(Studie aStudie)
  {
    boolean wasSet = false;
    if (aStudie == null)
    {
      return wasSet;
    }

    Studie existingStudie = studie;
    studie = aStudie;
    if (existingStudie != null && !existingStudie.equals(aStudie))
    {
      existingStudie.removeProsjekt(this);
    }
    studie.addProsjekt(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setBedrift(Bedrift aBedrift)
  {
    boolean wasSet = false;
    if (aBedrift == null)
    {
      return wasSet;
    }

    Bedrift existingBedrift = bedrift;
    bedrift = aBedrift;
    if (existingBedrift != null && !existingBedrift.equals(aBedrift))
    {
      existingBedrift.removeProsjekt(this);
    }
    bedrift.addProsjekt(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setEier(Bruker aEier)
  {
    boolean wasSet = false;
    if (aEier == null)
    {
      return wasSet;
    }

    Bruker existingEier = eier;
    eier = aEier;
    if (existingEier != null && !existingEier.equals(aEier))
    {
      existingEier.removeProsjekt(this);
    }
    eier.addProsjekt(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfArrangementdeltagelses()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Arrangementdeltagelse addArrangementdeltagelse(Arrangement aArrangement)
  {
    return new Arrangementdeltagelse(this, aArrangement);
  }

  public boolean addArrangementdeltagelse(Arrangementdeltagelse aArrangementdeltagelse)
  {
    boolean wasAdded = false;
    if (arrangementdeltagelses.contains(aArrangementdeltagelse)) { return false; }
    Prosjekt existingProsjekt = aArrangementdeltagelse.getProsjekt();
    boolean isNewProsjekt = existingProsjekt != null && !this.equals(existingProsjekt);
    if (isNewProsjekt)
    {
      aArrangementdeltagelse.setProsjekt(this);
    }
    else
    {
      arrangementdeltagelses.add(aArrangementdeltagelse);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeArrangementdeltagelse(Arrangementdeltagelse aArrangementdeltagelse)
  {
    boolean wasRemoved = false;
    //Unable to remove aArrangementdeltagelse, as it must always have a prosjekt
    if (!this.equals(aArrangementdeltagelse.getProsjekt()))
    {
      arrangementdeltagelses.remove(aArrangementdeltagelse);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addArrangementdeltagelseAt(Arrangementdeltagelse aArrangementdeltagelse, int index)
  {  
    boolean wasAdded = false;
    if(addArrangementdeltagelse(aArrangementdeltagelse))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfArrangementdeltagelses()) { index = numberOfArrangementdeltagelses() - 1; }
      arrangementdeltagelses.remove(aArrangementdeltagelse);
      arrangementdeltagelses.add(index, aArrangementdeltagelse);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveArrangementdeltagelseAt(Arrangementdeltagelse aArrangementdeltagelse, int index)
  {
    boolean wasAdded = false;
    if(arrangementdeltagelses.contains(aArrangementdeltagelse))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfArrangementdeltagelses()) { index = numberOfArrangementdeltagelses() - 1; }
      arrangementdeltagelses.remove(aArrangementdeltagelse);
      arrangementdeltagelses.add(index, aArrangementdeltagelse);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addArrangementdeltagelseAt(aArrangementdeltagelse, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    Studie placeholderStudie = studie;
    this.studie = null;
    if(placeholderStudie != null)
    {
      placeholderStudie.removeProsjekt(this);
    }
    Bedrift placeholderBedrift = bedrift;
    this.bedrift = null;
    if(placeholderBedrift != null)
    {
      placeholderBedrift.removeProsjekt(this);
    }
    Bruker placeholderEier = eier;
    this.eier = null;
    if(placeholderEier != null)
    {
      placeholderEier.removeProsjekt(this);
    }
    for(int i=arrangementdeltagelses.size(); i > 0; i--)
    {
      Arrangementdeltagelse aArrangementdeltagelse = arrangementdeltagelses.get(i - 1);
      aArrangementdeltagelse.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "navn" + ":" + getNavn()+ "," +
            "beskrivelse" + ":" + getBeskrivelse()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "studie = "+(getStudie()!=null?Integer.toHexString(System.identityHashCode(getStudie())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "bedrift = "+(getBedrift()!=null?Integer.toHexString(System.identityHashCode(getBedrift())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "eier = "+(getEier()!=null?Integer.toHexString(System.identityHashCode(getEier())):"null");
  }
}