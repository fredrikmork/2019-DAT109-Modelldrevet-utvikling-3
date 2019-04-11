<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4448.81a70243a modeling language!*/

class Brukergruppe
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Brukergruppe Attributes
  private $gruppenavn;
  private $gruppelevel;

  //Brukergruppe Associations
  private $brukers;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aGruppenavn, $aGruppelevel)
  {
    $this->gruppenavn = $aGruppenavn;
    $this->gruppelevel = $aGruppelevel;
    $this->brukers = array();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setGruppenavn($aGruppenavn)
  {
    $wasSet = false;
    $this->gruppenavn = $aGruppenavn;
    $wasSet = true;
    return $wasSet;
  }

  public function setGruppelevel($aGruppelevel)
  {
    $wasSet = false;
    $this->gruppelevel = $aGruppelevel;
    $wasSet = true;
    return $wasSet;
  }

  public function getGruppenavn()
  {
    return $this->gruppenavn;
  }

  public function getGruppelevel()
  {
    return $this->gruppelevel;
  }

  public function getBruker_index($index)
  {
    $aBruker = $this->brukers[$index];
    return $aBruker;
  }

  public function getBrukers()
  {
    $newBrukers = $this->brukers;
    return $newBrukers;
  }

  public function numberOfBrukers()
  {
    $number = count($this->brukers);
    return $number;
  }

  public function hasBrukers()
  {
    $has = $this->numberOfBrukers() > 0;
    return $has;
  }

  public function indexOfBruker($aBruker)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->brukers as $bruker)
    {
      if ($bruker->equals($aBruker))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public static function minimumNumberOfBrukers()
  {
    return 0;
  }

  public function addBrukerVia($aBrukernavn, $aPassord, $aUtgatt)
  {
    return new Bruker($aBrukernavn, $aPassord, $aUtgatt, $this);
  }

  public function addBruker($aBruker)
  {
    $wasAdded = false;
    if ($this->indexOfBruker($aBruker) !== -1) { return false; }
    $existingGruppe = $aBruker->getGruppe();
    $isNewGruppe = $existingGruppe != null && $this !== $existingGruppe;
    if ($isNewGruppe)
    {
      $aBruker->setGruppe($this);
    }
    else
    {
      $this->brukers[] = $aBruker;
    }
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeBruker($aBruker)
  {
    $wasRemoved = false;
    //Unable to remove aBruker, as it must always have a gruppe
    if ($this !== $aBruker->getGruppe())
    {
      unset($this->brukers[$this->indexOfBruker($aBruker)]);
      $this->brukers = array_values($this->brukers);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addBrukerAt($aBruker, $index)
  {  
    $wasAdded = false;
    if($this->addBruker($aBruker))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfBrukers()) { $index = $this->numberOfBrukers() - 1; }
      array_splice($this->brukers, $this->indexOfBruker($aBruker), 1);
      array_splice($this->brukers, $index, 0, array($aBruker));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveBrukerAt($aBruker, $index)
  {
    $wasAdded = false;
    if($this->indexOfBruker($aBruker) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfBrukers()) { $index = $this->numberOfBrukers() - 1; }
      array_splice($this->brukers, $this->indexOfBruker($aBruker), 1);
      array_splice($this->brukers, $index, 0, array($aBruker));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addBrukerAt($aBruker, $index);
    }
    return $wasAdded;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    foreach ($this->brukers as $aBruker)
    {
      $aBruker->delete();
    }
  }

}
?>