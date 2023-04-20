package com.example.board.model.board;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;


	@NoArgsConstructor
	@Data
	public class AttachedFile {
		private String original_filename;
		private String saved_filename;
		private Long file_size;
		
		private Long attachedFile_id;
		private Long board_id;
		
		public AttachedFile(String original_filename, String saved_filename, Long file_size) {
			this.original_filename = original_filename;
			this.saved_filename = saved_filename;
			this.file_size = file_size;
		}
		
		
}
