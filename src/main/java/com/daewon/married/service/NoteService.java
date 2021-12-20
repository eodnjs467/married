package com.daewon.married.service;

import com.daewon.married.dto.NoteDTO;
import com.daewon.married.entity.Member;
import com.daewon.married.entity.Note;
import com.daewon.married.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;

    public Long registerNote(NoteDTO noteDTO) {
        Note note = Note.builder()
                .num(noteDTO.getNum())
                .title(noteDTO.getTitle())
                .content(noteDTO.getContent())
                .writer(Member.builder().email(noteDTO.getWriterEmail()).build())
                .build();
        noteRepository.save(note);

        return note.getNum();
    }

    public Long modifyNote(NoteDTO noteDTO) {
        Note note = Note.builder()
                .title(noteDTO.getTitle())
                .content(noteDTO.getContent())
                .build();
        noteRepository.save(note);
        return note.getNum();
    }

    public void removeNote(Long num) {
        noteRepository.deleteById(num);
    }

    public Note getNote(Long num) {
        Optional<Note> note = noteRepository.getNoteByWriter(num);
        return note.orElse(null);
    }

    public List<Note> getListNoteWithWriter(String writerEmail) {
        List<Note> noteList = noteRepository.getNoteListByEmail(writerEmail);
        return noteList;
    }


}
