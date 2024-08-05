package org.app.dao;

import java.io.File;

public class SourceFileDAO {

  public File getFile() {
      return new File("src/main/resources/tickets.json");
  }
}

