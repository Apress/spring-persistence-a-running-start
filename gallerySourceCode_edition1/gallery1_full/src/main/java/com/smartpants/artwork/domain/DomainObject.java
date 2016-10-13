package com.smartpants.artwork.domain;

import java.io.Serializable;

public interface DomainObject extends Serializable {
   Long getId(); 
   void setId(Long id);
}
