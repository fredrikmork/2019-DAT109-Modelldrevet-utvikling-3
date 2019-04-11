/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4448.81a70243a modeling language!*/


import java.sql.Date;
import java.util.*;

// line 25 "model.ump"
// line 82 "model.ump"
public class Arrangement
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Arrangement Attributes
  private String navn;
  private String beskrivelse;
  private Date utgaar;

  //Arrangement Associations
  private Stemmemetode stemmemetode;
  private List<Arrangementdeltagelse> arrangementdeltagelses;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Arrangement(String aNavn, String aBeskrivelse, Date aUtgaar, Stemmemetode aStemmemetode)
  {
    navn = aNavn;
    beskrivelse = aBeskrivelse;
    utgaar = aUtgaar;
    boolean didAddStemmemetode = setStemmemetode(aStemmemetode);
    if (!didAddStemmemetode)
    {
      throw new RuntimeException("Unable to create arrangement due to stemmemetode");
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

  public boolean setUtgaar(Date aUtgaar)
  {
    boolean wasSet = false;
    utgaar = aUtgaar;
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

  public Date getUtgaar()
  {
    return utgaar;
  }
  /* Code from template association_GetOne */
  public Stemmemetode getStemmemetode()
  {
    return stemmemetode;
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
  public boolean setStemmemetode(Stemmemetode aStemmemetode)
  {
    boolean wasSet = false;
    if (aStemmemetode == null)
    {
      return wasSet;
    }

    Stemmemetode existingStemmemetode = stemmemetode;
    stemmemetode = aStemmemetode;
    if (existingStemmemetode != null && !existingStemmemetode.equals(aStemmemetode))
    {
      existingStemmemetode.removeArrangement(this);
    }
    stemmemetode.addArrangement(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfArrangementdeltagelses()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Arrangementdeltagelse addArrangementdeltagelse(Prosjekt aProsjekt)
  {
    return new Arrangementdeltagelse(aProsjekt, this);
  }

  public boolean addArrangementdeltagelse(Arrangementdeltagelse aArrangementdeltagelse)
  {
    boolean wasAdded = false;
    if (arrangementdeltagelses.contains(aArrangementdeltagelse)) { return false; }
    Arrangement existingArrangement = aArrangementdeltagelse.getArrangement();
    boolean isNewArrangement = existingArrangement != null && !this.equals(existingArrangement);
    if (isNewArrangement)
    {
      aArrangementdeltagelse.setArrangement(this);
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
    //Unable to remove aArrangementdeltagelse, as it must always have a arrangement
    if (!this.equals(aArrangementdeltagelse.getArrangement()))
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
    Stemmemetode placeholderStemmemetode = stemmemetode;
    this.stemmemetode = null;
    if(placeholderStemmemetode != null)
    {
      placeholderStemmemetode.removeArrangement(this);
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
            "  " + "utgaar" + "=" + (getUtgaar() != null ? !getUtgaar().equals(this)  ? getUtgaar().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "stemmemetode = "+(getStemmemetode()!=null?Integer.toHexString(System.identityHashCode(getStemmemetode())):"null");
  }
}