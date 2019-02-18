package br.com.beblue.desafio.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AlbumResource {

    @JsonProperty("albums")
    private Album album;

    public AlbumResource(Album album) {
        this.album = album;
    }

    @SuppressWarnings("unused")
    private AlbumResource() {
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public static class Album {

        private List<Item> items;

        public Album(List<Item> items) {
            this.items = items;
        }

        @SuppressWarnings("unused")
        private Album() {
        }

        public List<Item> getItems() {
            return items;
        }

        public void setItems(List<Item> items) {
            this.items = items;
        }

        public static class Item {

            private String id;
            private String name;

            public Item(String id, String name) {
                this.id = id;
                this.name = name;
            }

            @SuppressWarnings("unused")
            private Item() {
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

        }

    }
}