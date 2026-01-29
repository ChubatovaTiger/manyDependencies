import jetbrains.buildServer.configs.kotlin.*

version = "2025.11"
val numConfigurationsPerProject=900
project {
    buildType(Source)
    //val numConfigurationsPerProject=900
    for (i in 0..numProjects) {
        subProject {
            id("subProj_$i")
            name = "subProj $i"

         for (j in 0..numConfigurationsPerProject) {
                buildType {
                    id("subProj_bt_$i" + "_$j")
                    name = "bt $i $j"}

         }
        }
     }
}

object Source : BuildType({
    name = "source"

            for (j in 0..numConfigurationsPerProject) {
                dependencies {
                    snapshot(AbsoluteId("subProj_bt_$i" + "_$j")) {
                    reuseBuilds = ReuseBuilds.NO
                    }
                }
            }
})


