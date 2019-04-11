<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4448.81a70243a modeling language!*/

class Stemme
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Stemme Attributes
  private $epost;
  private $verdi;
  private $tidspunkt;

  //Stemme Associations
  private $arrangementdeltagelse;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aEpost, $aVerdi, $aTidspunkt, $aArrangementdeltagelse)
  {
    $this->epost = $aEpost;
    $this->verdi = $aVerdi;
    $this->tidspunkt = $aTidspunkt;
    $didAddArrangementdeltagelse = $this->setArrangementdeltagelse($aArrangementdeltagelse);
    if (!$didAddArrangementdeltagelse)
    {
      throw new Exception("Unable to create stemme due to arrangementdeltagelse");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setEpost($aEpost)
  {
    $wasSet = false;
    $this->epost = $aEpost;
    $wasSet = true;
    return $wasSet;
  }

  public function setVerdi($aVerdi)
  {
    $wasSet = false;
    $this->verdi = $aVerdi;
    $wasSet = true;
    return $wasSet;
  }

  public function setTidspunkt($aTidspunkt)
  {
    $wasSet = false;
    $this->tidspunkt = $aTidspunkt;
    $wasSet = true;
    return $wasSet;
  }

  public function getEpost()
  {
    return $this->epost;
  }

  public function getVerdi()
  {
    return $this->verdi;
  }

  public function getTidspunkt()
  {
    return $this->tidspunkt;
  }

  public function getArrangementdeltagelse()
  {
    return $this->arrangementdeltagelse;
  }

  public function setArrangementdeltagelse($aArrangementdeltagelse)
  {
    $wasSet = false;
    if ($aArrangementdeltagelse == null)
    {
      return $wasSet;
    }
    
    $existingArrangementdeltagelse = $this->arrangementdeltagelse;
    $this->arrangementdeltagelse = $aArrangementdeltagelse;
    if ($existingArrangementdeltagelse != null && $existingArrangementdeltagelse != $aArrangementdeltagelse)
    {
      $existingArrangementdeltagelse->removeStemme($this);
    }
    $this->arrangementdeltagelse->addStemme($this);
    $wasSet = true;
    return $wasSet;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $placeholderArrangementdeltagelse = $this->arrangementdeltagelse;
    $this->arrangementdeltagelse = null;
    $placeholderArrangementdeltagelse->removeStemme($this);
  }

}
?>