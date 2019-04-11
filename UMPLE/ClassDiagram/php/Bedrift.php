<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4448.81a70243a modeling language!*/

class Bedrift
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Bedrift Attributes
  private $navn;
  private $beskrivelse;

  //Bedrift Associations
  private $prosjekts;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aNavn, $aBeskrivelse)
  {
    $this->navn = $aNavn;
    $this->beskrivelse = $aBeskrivelse;
    $this->prosjekts = array();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setNavn($aNavn)
  {
    $wasSet = false;
    $this->navn = $aNavn;
    $wasSet = true;
    return $wasSet;
  }

  public function setBeskrivelse($aBeskrivelse)
  {
    $wasSet = false;
    $this->beskrivelse = $aBeskrivelse;
    $wasSet = true;
    return $wasSet;
  }

  public function getNavn()
  {
    return $this->navn;
  }

  public function getBeskrivelse()
  {
    return $this->beskrivelse;
  }

  public function getProsjekt_index($index)
  {
    $aProsjekt = $this->prosjekts[$index];
    return $aProsjekt;
  }

  public function getProsjekts()
  {
    $newProsjekts = $this->prosjekts;
    return $newProsjekts;
  }

  public function numberOfProsjekts()
  {
    $number = count($this->prosjekts);
    return $number;
  }

  public function hasProsjekts()
  {
    $has = $this->numberOfProsjekts() > 0;
    return $has;
  }

  public function indexOfProsjekt($aProsjekt)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->prosjekts as $prosjekt)
    {
      if ($prosjekt->equals($aProsjekt))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public static function minimumNumberOfProsjekts()
  {
    return 0;
  }

  public function addProsjektVia($aNavn, $aBeskrivelse, $aStudie, $aEier)
  {
    return new Prosjekt($aNavn, $aBeskrivelse, $aStudie, $this, $aEier);
  }

  public function addProsjekt($aProsjekt)
  {
    $wasAdded = false;
    if ($this->indexOfProsjekt($aProsjekt) !== -1) { return false; }
    $existingBedrift = $aProsjekt->getBedrift();
    $isNewBedrift = $existingBedrift != null && $this !== $existingBedrift;
    if ($isNewBedrift)
    {
      $aProsjekt->setBedrift($this);
    }
    else
    {
      $this->prosjekts[] = $aProsjekt;
    }
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeProsjekt($aProsjekt)
  {
    $wasRemoved = false;
    //Unable to remove aProsjekt, as it must always have a bedrift
    if ($this !== $aProsjekt->getBedrift())
    {
      unset($this->prosjekts[$this->indexOfProsjekt($aProsjekt)]);
      $this->prosjekts = array_values($this->prosjekts);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addProsjektAt($aProsjekt, $index)
  {  
    $wasAdded = false;
    if($this->addProsjekt($aProsjekt))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfProsjekts()) { $index = $this->numberOfProsjekts() - 1; }
      array_splice($this->prosjekts, $this->indexOfProsjekt($aProsjekt), 1);
      array_splice($this->prosjekts, $index, 0, array($aProsjekt));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveProsjektAt($aProsjekt, $index)
  {
    $wasAdded = false;
    if($this->indexOfProsjekt($aProsjekt) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfProsjekts()) { $index = $this->numberOfProsjekts() - 1; }
      array_splice($this->prosjekts, $this->indexOfProsjekt($aProsjekt), 1);
      array_splice($this->prosjekts, $index, 0, array($aProsjekt));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addProsjektAt($aProsjekt, $index);
    }
    return $wasAdded;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    foreach ($this->prosjekts as $aProsjekt)
    {
      $aProsjekt->delete();
    }
  }

}
?>