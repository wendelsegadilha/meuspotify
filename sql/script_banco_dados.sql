create database meuspotify;

use meuspotify;

create table tblMusica(
	idMusica int not null auto_increment,
	titulo varchar(100),
	artista varchar(100),
    album varchar(100),
	estilo int,
    linkArquivo text,
    constraint pk_musica primary key (idMusica)
);

create table tblUsuario(
	idUsuario int not null auto_increment,
    nome varchar(100),
    email varchar(100),
	senha varchar(20),
    constraint pk_usuario primary key (idUsuario)
);

create table tblPlaylist(
	idPlaylist int not null auto_increment,
    titulo varchar(150),
    idUsuario int not null,
    constraint pk_playlist primary key (idPlaylist),
    constraint fk_usuario foreign key (idUsuario) references tblUsuario (idUsuario)
);

create table tblMusicaPlaylist(
	idPlaylist int not null,
    idMusica int not null,
    constraint pk_mp primary key (idPlaylist,idMusica),
    constraint fk_pl foreign key (idPlaylist) references tblPlaylist (idPlaylist),
    constraint fk_mu foreign key (idMusica) references tblMusica (idMusica)
);

insert into tblUsuario values (null, "Teste 01", "teste01@teste.com", "123");




