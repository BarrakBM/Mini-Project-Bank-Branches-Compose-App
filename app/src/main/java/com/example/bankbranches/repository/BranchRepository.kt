package com.example.bankbranches.repository

import com.example.bankbranches.R
import com.example.bankbranches.data.Branch
import com.example.bankbranches.data.BranchType

class BranchRepository {

    // will fetch the dummy data we have for now (in future database)
    // we will use the enum to get the branches
    fun getBranches(): List<Branch>{
        return listOf(
            Branch(
                id = 1,
                name = "NBK Main Branch",
                type = BranchType.MAIN,
                address = "Abdullah Al-Salem Street, Kuwait City",
                phone = "+965 1801801",
                hours = "Sun-Thu: 8:30AM-2:30PM",
                location = "https://maps.google.com/?q=NBK+Main+Branch+Kuwait+City",
                imageUri = R.drawable.main_branch
            ),
            Branch(
                id = 2,
                name = "NBK Downtown Branch",
                type = BranchType.DOWNTOWN,
                address = "Fahad Al-Salem Street, Kuwait City",
                phone = "+965 1801801",
                hours = "Sun-Thu: 8:30AM-2:30PM",
                location = "https://maps.google.com/?q=NBK+Downtown+Kuwait+City",
            ),
            Branch(
                id = 3,
                name = "NBK Rawdha Branch",
                type = BranchType.RAWDA,
                address = "Rawdha Area, Block 1, Street 1",
                phone = "+965 1801801",
                hours = "Sun-Thu: 8:30AM-2:30PM",
                location = "https://maps.google.com/?q=NBK+Rawdha+Kuwait",
                imageUri = R.drawable.rawda
            ),
            Branch(
                id = 4,
                name = "NBK Avenues Mall",
                type = BranchType.AVENUES,
                address = "The Avenues Mall, Rai Area",
                phone = "+965 1801801",
                hours = "Daily: 10:00AM-10:00PM",
                location = "https://maps.google.com/?q=NBK+Avenues+Mall+Kuwait",
            ),
            Branch(
                id = 5,
                name = "NBK Al-Hamra Tower",
                type = BranchType.ALHAMRA,
                address = "Al-Hamra Tower, Kuwait City",
                phone = "+965 1801801",
                hours = "Sun-Thu: 8:30AM-2:30PM",
                location = "https://maps.google.com/?q=Al-Hamra+Tower+Kuwait",
            ),
            Branch(
                id = 6,
                name = "NBK ATM - City Center",
                type = BranchType.ATM,
                address = "City Center Mall, Salmiya",
                phone = "ATM Only",
                hours = "24/7",
                location = "https://maps.google.com/?q=City+Center+Mall+Salmiya+Kuwait",
            )
        )
    }


    fun getBranchById(id: Int): Branch? {
        return getBranches().find { branch -> branch.id == id }
    }

}