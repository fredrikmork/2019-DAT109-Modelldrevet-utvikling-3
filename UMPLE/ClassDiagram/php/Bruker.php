<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4448.81a70243a modeling language!*/

class Bruker
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Bruker Attributes
  private $brukernavn;
  private $passord;
  private $utgatt;

  //Bruker Associations
  private $gruppe;
  private $prosjekts;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aBrukernavn, $aPassord, $aUtgatt, $aGruppe)
  {
    $this->brukernavn = $aBrukernavn;
    $this->passord = $aPassord;
    $this->utgatt = $aUtgatt;
    $didAddGruppe = $this->setGruppe($aGruppe);
    if (!$didAddGruppe)
    {
      throw new Exception("Unable to create bruker due to gruppe");
    }
    $this->prosjekts = array();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setBrukernavn($aBrukernavn)
  {
    $wasSet = false;
    $this->brukernavn = $aBrukernavn;
    $wasSet = true;
    return $wasSet;
  }

  public function setPassord($aPassord)
  {
    $wasSet = false;
    $this->passord = $aPassord;
    $wasSet = true;
    return $wasSet;
  }

  public function setUtgatt($aUtgatt)
  {
    $wasSet = false;
    $this->utgatt = $aUtgatt;
    $wasSet = true;
    return $wasSet;
  }

  public function getBrukernavn()
  {
    return $this->brukernavn;
  }

  public function getPassord()
  {
    return $this->passord;
  }

  public function getUtgatt()
  {
    return $this->utgatt;
  }

  public function getGruppe()
  {
    return $this->gruppe;
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

  public function setGruppe($aGruppe)
  {
    $wasSet = false;
    if ($aGruppe == null)
    {
      return $wasSet;
    }
    
    $existingGruppe = $this->gruppe;
    $this->gruppe = $aGruppe;
    if ($existingGruppe != null && $existingGruppe != $aGruppe)
    {
      $existingGruppe->removeBruker($this);
    }
    $this->gruppe->addBruker($this);
    $wasSet = true;
    return $wasSet;
  }

  public static function minimumNumberOfProsjekts()
  {
    return 0;
  }

  public function addProsjektVia($aNavn, $aBeskrivelse, $aStudie, $aBedrift)
  {
    return new Prosjekt($aNavn, $aBeskrivelse, $aStudie, $aBedrift, $this);
  }

  public function addProsjekt($aProsjekt)
  {
    $wasAdded = false;
    if ($this->indexOfProsjekt($aProsjekt) !== -1) { return false; }
    $existingEier = $aProsjekt->getEier();
    $isNewEier = $existingEier != null && $this !== $existingEier;
    if ($isNewEier)
    {
      $aProsjekt->setEier($this);
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
    //Unable to remove aProsjekt, as it must always have a eier
    if ($this !== $aProsjekt->getEier())
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
    $placeholderGruppe = $this->gruppe;
    $this->gruppe = null;
    $placeholderGruppe->removeBruker($this);
    foreach ($this->prosjekts as $aProsjekt)
    {
      $aProsjekt->delete();
    }
  }

}
?>