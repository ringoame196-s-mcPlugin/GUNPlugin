package com.github.ringoame196_s_mcPlugin

import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player

class Gun {
    fun shot(player: Player) {
        // レイキャスト用の情報
        val eyeLocation = player.eyeLocation
        val direction = eyeLocation.direction

        // ブロックに当たるかを確認（50ブロックまで）
        val blockHit = player.world.rayTraceBlocks(eyeLocation, direction, 50.0)
        val maxDistance = blockHit?.hitPosition?.distance(eyeLocation.toVector()) ?: 50.0

        val result = player.world.rayTraceEntities(
            eyeLocation,
            direction,
            maxDistance
        ) { entity -> entity != player && entity is LivingEntity } ?: return

        val hit = result.hitEntity as LivingEntity
        hit.damage(8.0, player)

        // 命中処理
        hit.damage(8.0, player)
        player.sendMessage("${hit.name} に命中！")

        // 演出
        player.world.playSound(player.location, Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 1f, 1f)
        player.world.spawnParticle(Particle.CRIT, hit.location.add(0.0, 1.0, 0.0), 10)
    }
}
