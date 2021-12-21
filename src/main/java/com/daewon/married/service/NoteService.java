package com.daewon.married.service;

import com.daewon.married.dto.NoteDTO;
import com.daewon.married.entity.Member;
import com.daewon.married.entity.Note;
import com.daewon.married.repository.MemberRepository;
import com.daewon.married.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;
    private final MemberRepository memberRepository; // memberService 로 변경 -> service 에서 get 만들기
    @Transactional
    public Long registerNote(NoteDTO noteDTO) {// writer Member 에서 확인 해서 처리

        Note note = Note.builder()
                .num(noteDTO.getNum())
                .title(noteDTO.getTitle())
                .content(noteDTO.getContent())
                .writer(noteDTO.getWriterEmail())
                .modDate(LocalDateTime.now())
                .regDate(LocalDateTime.now())
                .build();
        noteRepository.save(note);

        return note.getNum();
    }

    public Long modifyNote(NoteDTO noteDTO) {

        Long num = noteDTO.getNum();

        Optional<Note> result = noteRepository.findById(num);

        if (result.isPresent()){
            Note note = result.get();

            note.changeTitle(noteDTO.getTitle());
            note.changeContent(noteDTO.getContent());
            noteRepository.save(note);
        }
        return num;
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
