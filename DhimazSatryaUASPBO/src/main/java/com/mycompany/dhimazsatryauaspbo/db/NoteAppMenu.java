/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dhimazsatryauaspbo.db;

import java.util.List;
import java.util.Scanner;

public class NoteAppMenu {
    private final NoteService noteService;
    private final Scanner scanner;

    public NoteAppMenu(String databasePath) {
        noteService = new NoteService(new DatabaseStorage(databasePath));
        scanner = new Scanner(System.in);
    }

    public void start() {
        boolean exit = false;
        while (!exit) {
            showMenu();
            int choice = getUserChoice();
            switch (choice) {
                case 1 -> addNote();
                case 2 -> showNotes();
                case 3 -> deleteNote();
                case 4 -> exit = true;
                default -> System.out.println("Pilihan tidak valid, coba lagi.");
            }
        }
    }

    private void showMenu() {
        System.out.println("\nNote App Menu oleh: Dhimaz Satrya NIM: 23201210.");
        System.out.println("1. Menambahkan Note");
        System.out.println("2. Tampilkan Note");
        System.out.println("3. Hapus Note");
        System.out.println("4. Keluar");
        System.out.print("Pilih opsi: ");
    }

    private int getUserChoice() {
        while (!scanner.hasNextInt()) {
            System.out.print("Masukkan opsi yang valid: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private void addNote() {
        scanner.nextLine(); // Clear buffer
        System.out.print("Masukkan note: ");
        String note = scanner.nextLine();
        noteService.createNote(note);
        System.out.println("note disimpan: " + note);
    }

    private void showNotes() {
        List<String> notes = noteService.readNotes();
        System.out.println("note tersimpan:");
        if (notes.isEmpty()) {
            System.out.println("Tidak ada note yang tersimpan.");
        } else {
            for (int i = 0; i < notes.size(); i++) {
                System.out.println((i + 1) + ". " + notes.get(i));
            }
        }
    }

    private void deleteNote() {
        showNotes();
        if (noteService.getNoteCount() > 0) {
            System.out.print("Pilih nomor note yang akan dihapus: ");
            int noteIndex = getUserChoice() - 1;
            if (noteIndex >= 0 && noteIndex < noteService.getNoteCount()) {
                String note = noteService.getNoteByIndex(noteIndex);
                noteService.deleteNote(note);
                System.out.println("note dihapus: " + note);
            } else {
                System.out.println("Nomor note tidak valid.");
            }
        }
    }
}
