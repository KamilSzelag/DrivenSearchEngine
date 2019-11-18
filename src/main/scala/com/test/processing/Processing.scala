package com.test.processing

import com.test.command.Command
import com.test.model.{FileNameWithPercentMatch, FileNameWithWords}
import com.test.searcher.FileSearcher.findTopNFiles

object Processing {
  def processStdinCommand(fileNameWithWords: Array[FileNameWithWords], searchText: String): Array[FileNameWithPercentMatch] = {
    if (searchText.startsWith(Command.startCommandSign)) {
      Command.processCommand(searchText)
      Array.empty
    } else {
      findTopNFiles(fileNameWithWords, searchText)
    }
  }
}
