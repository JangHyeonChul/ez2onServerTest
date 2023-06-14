package com.example.ez2onservertest.domain.main;


import com.example.ez2onservertest.global.musicDB.MusicDTO;
import com.example.ez2onservertest.global.musicDB.MusicMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MusicServiceImpl implements MusicService {

    MusicMapper music;

    public MusicServiceImpl(MusicMapper music) {
        this.music = music;
    }

    @Override
    public List<MusicDTO> getAllMusics() {
        return music.selectAllMusics();
    }
}
