PGDMP                          z            gestione_catalogo    14.1    14.1 0    -           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            .           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            /           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            0           1262    42638    gestione_catalogo    DATABASE     m   CREATE DATABASE gestione_catalogo WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Italian_Italy.1252';
 !   DROP DATABASE gestione_catalogo;
                postgres    false            ?            1259    73487    autore    TABLE     ?   CREATE TABLE public.autore (
    id bigint NOT NULL,
    cognome character varying(255) NOT NULL,
    nome character varying(255) NOT NULL
);
    DROP TABLE public.autore;
       public         heap    postgres    false            ?            1259    73486    autore_id_seq    SEQUENCE     v   CREATE SEQUENCE public.autore_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.autore_id_seq;
       public          postgres    false    211            1           0    0    autore_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.autore_id_seq OWNED BY public.autore.id;
          public          postgres    false    210            ?            1259    73496 	   categoria    TABLE     d   CREATE TABLE public.categoria (
    id bigint NOT NULL,
    nome character varying(255) NOT NULL
);
    DROP TABLE public.categoria;
       public         heap    postgres    false            ?            1259    73495    categoria_id_seq    SEQUENCE     y   CREATE SEQUENCE public.categoria_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.categoria_id_seq;
       public          postgres    false    213            2           0    0    categoria_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.categoria_id_seq OWNED BY public.categoria.id;
          public          postgres    false    212            ?            1259    73485    hibernate_sequence    SEQUENCE     {   CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.hibernate_sequence;
       public          postgres    false            ?            1259    73502    libri_autori    TABLE     b   CREATE TABLE public.libri_autori (
    libri_id bigint NOT NULL,
    autore_id bigint NOT NULL
);
     DROP TABLE public.libri_autori;
       public         heap    postgres    false            ?            1259    73507    libri_categorie    TABLE     h   CREATE TABLE public.libri_categorie (
    libri_id bigint NOT NULL,
    categoria_id bigint NOT NULL
);
 #   DROP TABLE public.libri_categorie;
       public         heap    postgres    false            ?            1259    73513    libro    TABLE     ?   CREATE TABLE public.libro (
    id bigint NOT NULL,
    anno_pubblicazione integer NOT NULL,
    prezzo double precision NOT NULL,
    titolo character varying(255) NOT NULL
);
    DROP TABLE public.libro;
       public         heap    postgres    false            ?            1259    73512    libro_id_seq    SEQUENCE     u   CREATE SEQUENCE public.libro_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.libro_id_seq;
       public          postgres    false    217            3           0    0    libro_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.libro_id_seq OWNED BY public.libro.id;
          public          postgres    false    216            ?            1259    73519    role    TABLE     V   CREATE TABLE public.role (
    id bigint NOT NULL,
    name character varying(255)
);
    DROP TABLE public.role;
       public         heap    postgres    false            ?            1259    73524 	   user_role    TABLE     \   CREATE TABLE public.user_role (
    user_id bigint NOT NULL,
    role_id bigint NOT NULL
);
    DROP TABLE public.user_role;
       public         heap    postgres    false            ?            1259    73529    user_spring    TABLE     ?   CREATE TABLE public.user_spring (
    id bigint NOT NULL,
    email character varying(255),
    is_active boolean NOT NULL,
    password character varying(255),
    user_name character varying(255)
);
    DROP TABLE public.user_spring;
       public         heap    postgres    false            {           2604    73490 	   autore id    DEFAULT     f   ALTER TABLE ONLY public.autore ALTER COLUMN id SET DEFAULT nextval('public.autore_id_seq'::regclass);
 8   ALTER TABLE public.autore ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    210    211    211            |           2604    73499    categoria id    DEFAULT     l   ALTER TABLE ONLY public.categoria ALTER COLUMN id SET DEFAULT nextval('public.categoria_id_seq'::regclass);
 ;   ALTER TABLE public.categoria ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    212    213    213            }           2604    73516    libro id    DEFAULT     d   ALTER TABLE ONLY public.libro ALTER COLUMN id SET DEFAULT nextval('public.libro_id_seq'::regclass);
 7   ALTER TABLE public.libro ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    217    216    217            !          0    73487    autore 
   TABLE DATA           3   COPY public.autore (id, cognome, nome) FROM stdin;
    public          postgres    false    211   j4       #          0    73496 	   categoria 
   TABLE DATA           -   COPY public.categoria (id, nome) FROM stdin;
    public          postgres    false    213   ?4       $          0    73502    libri_autori 
   TABLE DATA           ;   COPY public.libri_autori (libri_id, autore_id) FROM stdin;
    public          postgres    false    214   D5       %          0    73507    libri_categorie 
   TABLE DATA           A   COPY public.libri_categorie (libri_id, categoria_id) FROM stdin;
    public          postgres    false    215   ?5       '          0    73513    libro 
   TABLE DATA           G   COPY public.libro (id, anno_pubblicazione, prezzo, titolo) FROM stdin;
    public          postgres    false    217   ?5       (          0    73519    role 
   TABLE DATA           (   COPY public.role (id, name) FROM stdin;
    public          postgres    false    218   ?6       )          0    73524 	   user_role 
   TABLE DATA           5   COPY public.user_role (user_id, role_id) FROM stdin;
    public          postgres    false    219   ?6       *          0    73529    user_spring 
   TABLE DATA           P   COPY public.user_spring (id, email, is_active, password, user_name) FROM stdin;
    public          postgres    false    220   ?6       4           0    0    autore_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.autore_id_seq', 6, true);
          public          postgres    false    210            5           0    0    categoria_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.categoria_id_seq', 7, true);
          public          postgres    false    212            6           0    0    hibernate_sequence    SEQUENCE SET     @   SELECT pg_catalog.setval('public.hibernate_sequence', 4, true);
          public          postgres    false    209            7           0    0    libro_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.libro_id_seq', 10, true);
          public          postgres    false    216                       2606    73494    autore autore_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.autore
    ADD CONSTRAINT autore_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.autore DROP CONSTRAINT autore_pkey;
       public            postgres    false    211            ?           2606    73501    categoria categoria_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.categoria
    ADD CONSTRAINT categoria_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.categoria DROP CONSTRAINT categoria_pkey;
       public            postgres    false    213            ?           2606    73506    libri_autori libri_autori_pkey 
   CONSTRAINT     m   ALTER TABLE ONLY public.libri_autori
    ADD CONSTRAINT libri_autori_pkey PRIMARY KEY (libri_id, autore_id);
 H   ALTER TABLE ONLY public.libri_autori DROP CONSTRAINT libri_autori_pkey;
       public            postgres    false    214    214            ?           2606    73511 $   libri_categorie libri_categorie_pkey 
   CONSTRAINT     v   ALTER TABLE ONLY public.libri_categorie
    ADD CONSTRAINT libri_categorie_pkey PRIMARY KEY (libri_id, categoria_id);
 N   ALTER TABLE ONLY public.libri_categorie DROP CONSTRAINT libri_categorie_pkey;
       public            postgres    false    215    215            ?           2606    73518    libro libro_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.libro
    ADD CONSTRAINT libro_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.libro DROP CONSTRAINT libro_pkey;
       public            postgres    false    217            ?           2606    73523    role role_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.role DROP CONSTRAINT role_pkey;
       public            postgres    false    218            ?           2606    73528    user_role user_role_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public.user_role
    ADD CONSTRAINT user_role_pkey PRIMARY KEY (user_id, role_id);
 B   ALTER TABLE ONLY public.user_role DROP CONSTRAINT user_role_pkey;
       public            postgres    false    219    219            ?           2606    73535    user_spring user_spring_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.user_spring
    ADD CONSTRAINT user_spring_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.user_spring DROP CONSTRAINT user_spring_pkey;
       public            postgres    false    220            ?           2606    73556 %   user_role fka68196081fvovjhkek5m97n3y    FK CONSTRAINT     ?   ALTER TABLE ONLY public.user_role
    ADD CONSTRAINT fka68196081fvovjhkek5m97n3y FOREIGN KEY (role_id) REFERENCES public.role(id);
 O   ALTER TABLE ONLY public.user_role DROP CONSTRAINT fka68196081fvovjhkek5m97n3y;
       public          postgres    false    3209    219    218            ?           2606    73546 +   libri_categorie fkgvwbrv6ng3291ms36csjbacrl    FK CONSTRAINT     ?   ALTER TABLE ONLY public.libri_categorie
    ADD CONSTRAINT fkgvwbrv6ng3291ms36csjbacrl FOREIGN KEY (categoria_id) REFERENCES public.categoria(id);
 U   ALTER TABLE ONLY public.libri_categorie DROP CONSTRAINT fkgvwbrv6ng3291ms36csjbacrl;
       public          postgres    false    3201    215    213            ?           2606    73536 (   libri_autori fkihbnvnb6gi5ihakbto8w1u240    FK CONSTRAINT     ?   ALTER TABLE ONLY public.libri_autori
    ADD CONSTRAINT fkihbnvnb6gi5ihakbto8w1u240 FOREIGN KEY (autore_id) REFERENCES public.autore(id);
 R   ALTER TABLE ONLY public.libri_autori DROP CONSTRAINT fkihbnvnb6gi5ihakbto8w1u240;
       public          postgres    false    3199    214    211            ?           2606    73541 (   libri_autori fkiic0glikwfgnenqsq3ofp7muu    FK CONSTRAINT     ?   ALTER TABLE ONLY public.libri_autori
    ADD CONSTRAINT fkiic0glikwfgnenqsq3ofp7muu FOREIGN KEY (libri_id) REFERENCES public.libro(id);
 R   ALTER TABLE ONLY public.libri_autori DROP CONSTRAINT fkiic0glikwfgnenqsq3ofp7muu;
       public          postgres    false    214    3207    217            ?           2606    73561 %   user_role fkjnbh64dhuo55gh2jd9d21d245    FK CONSTRAINT     ?   ALTER TABLE ONLY public.user_role
    ADD CONSTRAINT fkjnbh64dhuo55gh2jd9d21d245 FOREIGN KEY (user_id) REFERENCES public.user_spring(id);
 O   ALTER TABLE ONLY public.user_role DROP CONSTRAINT fkjnbh64dhuo55gh2jd9d21d245;
       public          postgres    false    220    219    3213            ?           2606    73551 *   libri_categorie fkpvebd9fsdmceh2n9li1cdxw0    FK CONSTRAINT     ?   ALTER TABLE ONLY public.libri_categorie
    ADD CONSTRAINT fkpvebd9fsdmceh2n9li1cdxw0 FOREIGN KEY (libri_id) REFERENCES public.libro(id);
 T   ALTER TABLE ONLY public.libri_categorie DROP CONSTRAINT fkpvebd9fsdmceh2n9li1cdxw0;
       public          postgres    false    3207    215    217            !   h   x??I
?@????i?`. H%?l?P?&Eu(?????wB]̲O??]ܕgtRɨ???\~?/xd34!??þ?Y?-??C>3??K??)a?????J$??      #   R   x?˻@@?z??H?{???V3d?̎??????19???t?????<?|?J????A-?hhdr?_Z	???s??>?      $   0   x?Ź  ???#/a??:?.!	cI?AKp?苤m?"????      %   1   x?ǹ  ??*???z??:?`?$???F?+Qhk?=???i?7$}?<?      '   ?   x?]??
1E뙯Hg'ɾt?T|?6666?n?????~???{9?{RV??!???P??S?29?
v?L??o???ܠ?8?KOXs]2?[oĕĝ?g???;?????c/V??E/zmmޙ???p?x?x)%??QA?zP?Ɨv???Z??u?Q???C d?v?~???MvL?*[u?w?0,n&z|.?v?ID      (   !   x?3???q?wt????2?pB?]??b???? s??      )      x?3?4?2?4?????? ??      *   ?   x?m?;?0  Й????*	nFE??4B??ҒX?B?#?7??w??ʕ(׼RT??U??SӶ?)????1??ҍY?X??a??;??]0???B?x\?9u?????ѷE???l??!w???ƺ??{N598	ΠΛ??t!^?y?j)?o ?vi9     