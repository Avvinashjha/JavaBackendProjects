package com.dailycoder.cacheManager.controller;
import com.dailycoder.cacheManager.dto.CacheRequest;
import com.dailycoder.cacheManager.dto.CacheResponse;
import com.dailycoder.cacheManager.services.CacheService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Cache Manager", description = "Cache management operations")
@RestController
@RequestMapping("/api/v1/cache")
public class CacheController {

    @Autowired
    private final CacheService cacheService;

    public CacheController(CacheService cacheService) {
        this.cacheService = cacheService;
    }


    @Operation(
        summary = "Save cache with TTL",
        description = "Saves a cache entry with a specified TTL (Time to Live)"
    )
    @PostMapping("/save")
    public ResponseEntity<CacheResponse> save(@RequestBody CacheRequest request){
        try{
            cacheService.save(
                request.getKey(),
                request.getValue(),
                request.getTtl(),
                request.getTimeUnit()
            );
            return ResponseEntity.ok(new CacheResponse(true, "Cache saved successfully", null));
        }catch (Exception e){
            return ResponseEntity.status(500).body(new CacheResponse(false, e.getMessage(), null));
        }
    }

    @Operation(
        summary = "Save cache without TTL",
        description = "Saves a cache entry without a specified TTL (Time to Live)"
    )
    @PostMapping("/saveWithoutTTL")
    public ResponseEntity<CacheResponse> saveWithoutTTL(@RequestBody CacheRequest request, @Parameter(name = "X-CSRF-TOKEN", description = "CSRF Token", required = false) @RequestHeader("X-CSRF-TOKEN") String csrfToken){
        try{
            cacheService.saveWithoutTTK(request.getKey(), request.getValue());
            return ResponseEntity.ok(new CacheResponse(true, "Cache saved successfully without TTL", null));
        }catch (Exception e){
            return ResponseEntity.status(500).body(new CacheResponse(false, e.getMessage(), null));
        }
    }

    @Operation(
        summary = "Remove cache",
        description = "Removes a cache entry by key"
    )
    @GetMapping("/get/{key}")
    public ResponseEntity<CacheResponse> get(@PathVariable String key){
        try{
            Object value = cacheService.get(key);
            if(value != null){
                return ResponseEntity.ok(new CacheResponse(true, "Cache retrieved successfully", value));
            }else{
                return ResponseEntity.status(404).body(new CacheResponse(false, "Cache not found", null));
            }
        }catch (Exception e){
            return ResponseEntity.status(500).body(new CacheResponse(false, e.getMessage(), null));
        }
    }

    @Operation(
        summary = "Remove cache",
        description = "Removes a cache entry by key"
    )
    @DeleteMapping("/remove/{key}")
    public ResponseEntity<CacheResponse> remove(@PathVariable String key){
        try{
            boolean removed = cacheService.remove(key);
            if(removed){
                return ResponseEntity.ok(new CacheResponse(true, "Cache removed successfully", null));
            }else{
                return ResponseEntity.status(404).body(new CacheResponse(false, "Cache not found", null));
            }
        }catch (Exception e){
            return ResponseEntity.status(500).body(new CacheResponse(false, e.getMessage(), null));
        }
    }

    @Operation(
        summary = "Check cache availability",
        description = "Checks if the cache is available"
    )
    @GetMapping("/isAvailable")
    public ResponseEntity<CacheResponse> isAvailable(){
        try{
            boolean available = cacheService.isAvailable();
            return ResponseEntity.ok(new CacheResponse(true, "Cache availability checked", available));
        }catch (Exception e){
            return ResponseEntity.status(500).body(new CacheResponse(false, e.getMessage(), null));
        }
    }

    @Operation(
        summary = "Check if key exists",
        description = "Checks if a cache entry exists by key"
    )
    @GetMapping("/hasKey/{key}")
    public ResponseEntity<CacheResponse> hasKey(@PathVariable String key){
        try{
            boolean exists = cacheService.hasKey(key);
            return ResponseEntity.ok(new CacheResponse(true, "Key existence checked", exists));
        }catch (Exception e){
            return ResponseEntity.status(500).body(new CacheResponse(false, e.getMessage(), null));
        }
    }
}
